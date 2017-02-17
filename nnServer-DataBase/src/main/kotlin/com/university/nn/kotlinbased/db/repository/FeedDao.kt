package com.university.nn.kotlinbased.db.repository

import com.rometools.rome.feed.synd.SyndFeed

/**
 * Created by win on 2/17/17.
 */
interface FeedDao {
    fun getFeed(url: String):SyndFeed
}