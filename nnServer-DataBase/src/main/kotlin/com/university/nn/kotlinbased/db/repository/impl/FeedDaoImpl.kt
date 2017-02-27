package com.university.nn.kotlinbased.db.repository.impl

import com.rometools.rome.io.SyndFeedInput
import com.rometools.rome.io.XmlReader
import com.university.nn.kotlinbased.db.repository.FeedDao
import com.university.nn.kotlinbased.db.response.ResponseFeed
import com.university.nn.kotlinbased.db.utils.log
import com.university.nn.kotlinbased.db.utils.toClass
import com.university.nn.kotlinbased.db.utils.toJson
import com.university.nn.kotlinbased.utils.Container
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.stereotype.Repository
import redis.clients.jedis.Jedis
import java.net.URL
import java.util.*

@Repository
open class FeedDaoImpl : FeedDao {

    companion object {
        val USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0"
        val TIME_TO_KEEP = 60 * 10
        val LOCALHOST = "localhost"
        val TIMEOUT = 10000
        val PAGES_TO_SEARCH = 5
        val RSS_TYPES = mutableListOf(
                "application/rss+xml",
                "application/atom+xml",
                "application/rdf+xml",
                "application/rss",
                "application/atom",
                "application/rdf",
                "text/rss+xml",
                "text/atom+xml",
                "text/rdf+xml",
                "text/rss",
                "text/atom",
                "text/rdf"
        )
    }

    override fun getFeeds(urls: List<String>): List<ResponseFeed> {
        log("start get feeds")
        val jedis = Jedis(LOCALHOST)
        val key = urls.toString()
        log("jedis key = $key")
        val wraper: ListFeedsWraper? = jedis.get(key).toClass()
        log("wrapper class = $wraper")
        if (wraper?.list?.isNotEmpty() != null) {
            log("wrapper is not empty or null, return wrapper list = ${wraper?.list}")
            return wraper?.list ?: mutableListOf()
        }
        log("wrapper is not in redis, performinge get dates")
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
            log("dates ar not empty = $dates")
            jedis.setex(key, TIME_TO_KEEP, ListFeedsWraper(dates).toJson())
        }
        return dates
    }

    override fun searchFeeds(key: String): List<Container> {
        log("startting search feeds")
        val jedis = Jedis(LOCALHOST)
        val wraper: ListContainerWraper? = jedis.get(key).toClass()
        log("wrapper class = $wraper")
        if (wraper?.list?.isNotEmpty() != null) {
            log("wrapper is not null")
            return wraper?.list ?: mutableListOf()
        }
        log("wrapper is null")
        val list = ArrayList<Container>()
        log("list = $list")
        val baseUrl = "http://www.google.com/search?num=$PAGES_TO_SEARCH&q=$key".replace(" ","%20")
        log("base url = $baseUrl")
        val document = Jsoup.connect(baseUrl).userAgent(USER_AGENT).timeout(TIMEOUT).get()
        log("document = $document")
        val cite = document.getElementsByTag("cite")
                .map { cite -> cite.html().replace("<b>|</b>".toRegex(), "") }
                .filter { cite -> cite.matches(("""^(www|https://www|https://|https://blog)?[.]?$key.*[.](com|ru|en|md|ua).*""").toRegex()) && !cite.contains(("?")) }
        log("cite = $cite")
        cite.forEach { cite ->
            log("in cite for each ")
            var url = cite
            log("in cite for each url = $url")
            if (!cite.contains("http"))
                log("in cite for each, cite dont contain http")
            url = "http://$url"
            val container: Container = Container()
            log("in cite for each, creating container = $container")
            val doc = Jsoup.connect(url).userAgent(USER_AGENT).timeout(TIMEOUT).get()
            log("in cite for each, doc = $doc")
            val icon = doc.head().select("link[href~=.*\\.(ico|png)]").first()
            log("in cite for each, icon = $icon")
            if (icon != null) {
                log("in cite for each, icon is not null ")
                val iconlink = icon.attr("href")
                container.iconUrl = if (iconlink.contains("http")) iconlink else url + iconlink
            }

            for (rssType in RSS_TYPES) {
                if (doc.html().contains(rssType)) {
                    log("in cite for each, doc.html contains aplication rss+xl")
                    val link = doc.head().select("link[type=$rssType]").attr("href")
                    log("in cite for each, link = $link")
                    container.feeds.add(if (link.contains("http".toRegex())) link else url + link)
                    log("in cite for each, container = $container")
                    break
                }
            }
            log("in cite for each, code dont contain rss + xml")
            deepExtractFeed(container, doc)
            log("in cite for each, list add conainer = $container")
            list.add(container)
        }
        log("before finish")
        val dates = list.filter { elem -> elem.feeds.size != 0 }
        log("dates $dates")
        if (dates.isNotEmpty()) {
            log("save in redis")
            jedis.set(key, ListContainerWraper(dates).toJson())
        }
        log("return dates = $dates")
        return dates
    }

    private fun deepExtractFeed(container: Container, doc: Document) {
        log("start deep extract")
        val hrefs = doc.select("a[href]")
                .map { href -> href.attr("href") }
                .filter { href ->
                    (href.contains("feed".toRegex()) || href.contains("rss".toRegex()))
                            && !href.contains("feedback")
                            && href.contains("http".toRegex())
                            && !href.contains("(yahoo|facebook|twitter)".toRegex())
                }
        log("hrefps = $hrefs")
        log("")
        hrefs.forEach {
            href ->
            log("href foreach, ")
            val docFeed = Jsoup.connect(href).userAgent(USER_AGENT).timeout(TIMEOUT).get()
            log("href foreach, docFeed = $docFeed")
            for (rssType in RSS_TYPES) {
                if (docFeed.html().contains(rssType)) {
                    log("href foreach, contains aplication rss + xml")
                    container.feeds.add(href)
                }
            }
            log("href foreach, dont contains search deep")
            deepExtractFeed(container, docFeed)
        }
    }
}

/**
 * wrappers to data lists, because is
 * hard to serialize and desierialize lists to json
 */
class ListContainerWraper(val list: List<Container>)

class ListFeedsWraper(val list: List<ResponseFeed>)