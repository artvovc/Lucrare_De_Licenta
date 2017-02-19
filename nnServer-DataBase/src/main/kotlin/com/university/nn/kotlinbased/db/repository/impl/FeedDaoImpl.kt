package com.university.nn.kotlinbased.db.repository.impl

import com.rometools.rome.feed.synd.SyndFeed
import com.rometools.rome.io.SyndFeedInput
import com.rometools.rome.io.XmlReader
import com.university.nn.kotlinbased.db.repository.FeedDao
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.stereotype.Repository
import java.net.URL
import java.util.*

@Repository
open class FeedDaoImpl : FeedDao {
    override fun getFeed(url: String): SyndFeed = SyndFeedInput().build(XmlReader(URL(url)))

    override fun searchFeed(key: String): List<String> {
        println(key)
        val doc: Document = Jsoup.connect(key).get()
        println(doc)
        val element = doc.head().select("link[href~=.*\\.(ico|png)]").first()
        println(element)
        println(element.attr("href"))
        return ArrayList()
    }
}