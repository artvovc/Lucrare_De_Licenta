package com.university.nn.kotlinbased.services

import com.university.nn.kotlinbased.db.model.FeedData
import com.university.nn.kotlinbased.db.model.Item
import com.university.nn.kotlinbased.db.repository.ItemDao
import com.university.nn.kotlinbased.utils.Container
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FeedService
@Autowired
constructor(private val itemDao: ItemDao) {
    fun getFeeds(urls: List<String>): List<Item> = itemDao.getItems(urls)
    fun searchFeeds(key: String): Container = itemDao.searchItems(key)
    fun findByTag(tag: String): List<FeedData> {
        return itemDao.searchByTag(tag)
    }

    fun insertFeedData(fedData: FeedData) {
        itemDao.saveFeedData(fedData)
    }

    fun getAllTags() = itemDao.getAllCategories()
}