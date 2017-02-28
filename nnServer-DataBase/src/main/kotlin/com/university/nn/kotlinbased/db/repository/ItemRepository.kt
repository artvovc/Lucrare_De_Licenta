package com.university.nn.kotlinbased.db.repository

import com.university.nn.kotlinbased.db.model.Item
import org.springframework.data.mongodb.repository.MongoRepository

interface ItemRepository : MongoRepository<Item, String> {
    fun findByFeedLinkIn(feedLink: List<String>): List<Item>
}