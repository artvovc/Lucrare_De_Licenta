package com.university.nn.kotlinbased.db.repository.impl

import com.rometools.rome.feed.synd.SyndFeed
import com.rometools.rome.io.SyndFeedInput
import com.rometools.rome.io.XmlReader
import com.university.nn.kotlinbased.db.repository.FeedDao
import com.university.nn.kotlinbased.utils.Container
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.stereotype.Repository
import java.net.URL
import java.util.*

@Repository
open class FeedDaoImpl : FeedDao {
    override fun getFeeds(urls: List<String>): List<SyndFeed> = urls.map { url -> SyndFeedInput().build(XmlReader(URL(url))) }

    override fun searchFeeds(key: String): List<Container> {
        val document = Jsoup
                .connect("http://www.google.com/search?num=20&q=$key")
                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0")
                .get()
        val cite = document.getElementsByTag("cite")
                .map { cite -> cite.html().replace("<b>|</b>".toRegex(), "") }
                .filter { cite -> cite.matches(("""^(www|https://www|https://|https://blog)?[.]?$key.*[.](com|ru|en|md|ua).*""").toRegex()) && !cite.matches((""".*[?].*""").toRegex()) }
        val list = ArrayList<Container>()
        cite.forEach { cite ->
            var url = cite
            if (!cite.contains("http".toRegex()))
                url = "http://$url"
            val container: Container = Container()
            val doc: Document = Jsoup.connect(url).get()
            val icon = doc.head().select("link[href~=.*\\.(ico|png)]").first()
            val iconlink = icon.attr("href")
            container.iconUrl = if (iconlink.contains("http")) iconlink else url+iconlink
            if(doc.html().contains("application/rss+xml")){
                val link = doc.head().select("link[type=application/rss+xml]").attr("href")
                container.feeds.add(
                        if(link.contains("http".toRegex())) link else url+link
                )
            }
            else deepExtractFeed(container, doc)
            list.add(container)
        }
        return list.filter { elem -> elem.feeds.size!=0 }
    }

    private fun deepExtractFeed(container: Container, doc: Document) {
        val hrefs = doc.select("a[href]")
                .map { href -> href.attr("href") }
                .filter { href -> href.contains("feed".toRegex()) && !href.contains("feedback") && href.contains("http".toRegex()) && !href.contains("(yahoo|facebook|twitter)".toRegex()) }
        hrefs.forEach {
            href ->
            val docFeed: Document = Jsoup.connect(href).get()
            if (docFeed.html().contains("application/rss+xml"))
                container.feeds.add(href)
            else {
                deepExtractFeed(container, Jsoup.connect(href).get())
            }
        }
    }
}