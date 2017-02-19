package com.university.nn.kotlinbased.db.repository

import com.rometools.rome.feed.synd.SyndFeed

interface FeedDao {
    fun getFeed(url: String):SyndFeed
    fun searchFeed(key: String):List<String>
}