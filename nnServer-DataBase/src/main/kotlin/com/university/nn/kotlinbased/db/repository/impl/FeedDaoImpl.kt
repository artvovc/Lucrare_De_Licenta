package com.university.nn.kotlinbased.db.repository.impl

import com.rometools.rome.feed.synd.SyndFeed
import com.rometools.rome.io.SyndFeedInput
import com.rometools.rome.io.XmlReader
import com.university.nn.kotlinbased.db.repository.FeedDao
import org.springframework.stereotype.Repository
import java.net.URL

@Repository
open class FeedDaoImpl : FeedDao {
    override fun getFeed(url: String): SyndFeed = SyndFeedInput().build(XmlReader(URL(url)))
}