package com.university.nn.kotlinbased.db.repository

import com.university.nn.kotlinbased.db.model.FeedData
import org.springframework.data.mongodb.repository.MongoRepository

interface FeedDataRepository : MongoRepository<FeedData, String> {
    fun findByTags(tag: String): List<FeedData>
}