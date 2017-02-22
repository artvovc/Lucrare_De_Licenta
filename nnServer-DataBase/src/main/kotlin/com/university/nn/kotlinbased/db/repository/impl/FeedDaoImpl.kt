package com.university.nn.kotlinbased.db.repository.impl

import com.rometools.rome.feed.synd.SyndFeed
import com.rometools.rome.io.SyndFeedInput
import com.rometools.rome.io.XmlReader
import com.university.nn.kotlinbased.db.repository.FeedDao
import com.university.nn.kotlinbased.utils.Container
import org.apache.log4j.Logger
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.stereotype.Repository
import java.net.URL
import java.util.*

@Repository
open class FeedDaoImpl : FeedDao {

    internal var logger = Logger.getLogger(FeedDaoImpl::class.java)

    companion object{
        val useragent = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0"
    }

    override fun getFeeds(urls: List<String>): List<SyndFeed> = urls.map { url -> SyndFeedInput().build(XmlReader(URL(url))) }

    override fun searchFeeds(key: String): List<Container> {
        val list = ArrayList<Container>()
        logger.warn(key)
        val baseUrl = "http://www.google.com/search?num=20&q=$key"
        logger.warn(baseUrl)
        val connection = Jsoup.connect(baseUrl).userAgent(useragent)
        val response = connection.execute()
        if (response.statusCode() == 200) {
            val document = connection.get()
            val cite = document.getElementsByTag("cite")
                    .map { cite -> cite.html().replace("<b>|</b>".toRegex(), "") }
                    .filter { cite -> !cite.contains(("?")) && !cite.contains("forum") }
            logger.warn("cite " + cite.size)
            cite.forEach { cite ->
                var url = cite
                if (!cite.contains("http".toRegex()))
                    url = "http://$url"
                logger.warn("URL : " + url)
                val container: Container = Container()
                val subConnection = Jsoup.connect(url).userAgent(useragent)
                val subResponse = subConnection.execute()
                if (subResponse.statusCode() == 200) {
                    val doc = subConnection.get()
                    val icon = doc.head().select("link[href~=.*\\.(ico|png)]").first()
                    if(icon != null){
                        val iconlink = icon.attr("href")
                        container.iconUrl = if (iconlink.contains("http")) iconlink else url + iconlink
                    }
                    if (doc.html().contains("application/rss+xml")) {
                        logger.warn("IF STATEMENT")
                        val link = doc.head().select("link[type=application/rss+xml]").attr("href")
                        container.feeds.add(
                                if (link.contains("http".toRegex())) link else url + link
                        )
                    } else {
                        logger.warn("deepExtractFeed")
                        deepExtractFeed(container, doc)
                    }
                    list.add(container)
                }
            }
        }
        return list.filter { elem -> elem.feeds.size != 0 }
    }

    private fun deepExtractFeed(container: Container, doc: Document) {
        val hrefs = doc.select("a[href]")
                .map { href -> href.attr("href") }
                .filter { href -> href.contains("feed".toRegex()) && !href.contains("feedback") && href.contains("http".toRegex()) && !href.contains("(yahoo|facebook|twitter)".toRegex()) }
        hrefs.forEach {
            href ->
            val connection = Jsoup.connect(href).userAgent(useragent)
            val response = connection.execute()
            if (response.statusCode() == 200) {
                val docFeed = connection.get()
                if (docFeed.html().contains("application/rss+xml"))
                    container.feeds.add(href)
                else {
                    deepExtractFeed(container, docFeed)
                }
            }
        }
    }
}