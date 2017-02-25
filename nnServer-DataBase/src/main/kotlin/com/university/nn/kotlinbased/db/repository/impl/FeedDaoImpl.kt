package com.university.nn.kotlinbased.db.repository.impl

import com.rometools.rome.io.SyndFeedInput
import com.rometools.rome.io.XmlReader
import com.university.nn.kotlinbased.db.repository.FeedDao
import com.university.nn.kotlinbased.db.response.ResponseFeed
import com.university.nn.kotlinbased.db.utils.toClass
import com.university.nn.kotlinbased.db.utils.toJson
import com.university.nn.kotlinbased.utils.Container
import org.apache.log4j.Logger
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.stereotype.Repository
import redis.clients.jedis.Jedis
import java.net.URL
import java.util.*

@Repository
open class FeedDaoImpl : FeedDao {

    internal var logger = Logger.getLogger(FeedDaoImpl::class.java)

    companion object {
        val USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0"
        val TIME_TO_KEEP = 1000*60*20
    }

    override fun getFeeds(urls: List<String>): List<ResponseFeed> {
        val jedis = Jedis("localhost")
        val key = urls.toString()
        val wraper: ListFeedsWraper? = jedis.get(key).toClass()
        if (wraper?.list?.isNotEmpty() != null) {
            return wraper?.list ?: mutableListOf()
        }
        val dates = urls.map { url -> SyndFeedInput().build(XmlReader(URL(url))) }
                .flatMap { syndFeed ->
                    syndFeed.entries.map { entry ->
                        ResponseFeed(
                                entry.title,
                                entry.author,
                                entry.link,
                                entry.description.value,
                                entry.publishedDate
                        )
                    }
                }
        if (dates.isNotEmpty()) {
            jedis.setex(key,TIME_TO_KEEP, ListFeedsWraper(dates).toJson())
        }
        return dates
    }

    override fun searchFeeds(key: String): List<Container> {
        val jedis = Jedis("localhost")
        val wraper: ListContainerWraper? = jedis.get(key).toClass()
        if (wraper?.list?.isNotEmpty() != null) {
            return wraper?.list ?: mutableListOf()
        }

        val list = ArrayList<Container>()
        val baseUrl = "http://www.google.com/search?num=5&q=$key"
        val document = Jsoup.connect(baseUrl).userAgent(USER_AGENT).timeout(10000).get()
        val cite = document.getElementsByTag("cite")
                .map { cite -> cite.html().replace("<b>|</b>".toRegex(), "") }
                .filter { cite -> cite.matches(("""^(www|https://www|https://|https://blog)?[.]?$key.*[.](com|ru|en|md|ua).*""").toRegex()) && !cite.contains(("?")) }
        cite.forEach { cite ->
            var url = cite
            if (!cite.contains("http"))
                url = "http://$url"
            val container: Container = Container()
            val doc = Jsoup.connect(url).userAgent(USER_AGENT).timeout(10000).get()
            val icon = doc.head().select("link[href~=.*\\.(ico|png)]").first()
            if (icon != null) {
                val iconlink = icon.attr("href")
                container.iconUrl = if (iconlink.contains("http")) iconlink else url + iconlink
            }
            if (doc.html().contains("application/rss+xml")) {
                val link = doc.head().select("link[type=application/rss+xml]").attr("href")
                container.feeds.add(if (link.contains("http".toRegex())) link else url + link)
            } else {
                deepExtractFeed(container, doc)
            }
            list.add(container)
        }

        logger.warn("before finish")

        val dates = list.filter { elem -> elem.feeds.size != 0 }
        logger.warn("dates $dates")
        if (dates.isNotEmpty()) {
            jedis.set(key, ListContainerWraper(dates).toJson())
        }
        return dates
    }

    private fun deepExtractFeed(container: Container, doc: Document) {
        val hrefs = doc.select("a[href]")
                .map { href -> href.attr("href") }
                .filter { href -> href.contains("feed".toRegex()) && !href.contains("feedback") && href.contains("http".toRegex()) && !href.contains("(yahoo|facebook|twitter)".toRegex()) }
        hrefs.forEach {
            href ->
            val docFeed = Jsoup.connect(href).userAgent(USER_AGENT).timeout(10000).get()
            if (docFeed.html().contains("application/rss+xml"))
                container.feeds.add(href)
            else {
                deepExtractFeed(container, docFeed)
            }

        }
    }
}

class ListContainerWraper(val list: List<Container>)
class ListFeedsWraper(val list: List<ResponseFeed>)