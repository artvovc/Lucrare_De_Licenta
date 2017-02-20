package com.university.nn.kotlinbased.db.repository

import com.rometools.rome.feed.synd.SyndFeed
import com.university.nn.kotlinbased.utils.Container

interface FeedDao {
    fun getFeeds(urls: List<String>): List<SyndFeed>
    fun searchFeed(key: String): List<Container>
}