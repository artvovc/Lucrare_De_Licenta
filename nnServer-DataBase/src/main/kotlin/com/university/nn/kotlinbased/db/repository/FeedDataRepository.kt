package com.university.nn.kotlinbased.db.repository

import com.university.nn.kotlinbased.db.model.FeedData
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created by pc on 3/3/17.
 */
interface FeedDataRepository : MongoRepository<FeedData, String> {
    fun findByTags(tag: String): List<FeedData>
//    fun insertFeedData(fedData: FeedData)
}