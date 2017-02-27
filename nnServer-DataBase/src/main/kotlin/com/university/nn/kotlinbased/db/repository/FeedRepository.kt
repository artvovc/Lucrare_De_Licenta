package com.university.nn.kotlinbased.db.repository

import com.university.nn.kotlinbased.db.model.Feed
import org.springframework.data.mongodb.repository.MongoRepository

interface FeedRepository : MongoRepository<Feed, String> {
    fun findByFeedLinkIn(feedLink: List<String>): List<Feed>
}