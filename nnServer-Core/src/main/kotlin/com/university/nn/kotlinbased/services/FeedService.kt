package com.university.nn.kotlinbased.services

import com.university.nn.kotlinbased.db.model.Feed
import com.university.nn.kotlinbased.db.repository.FeedDao
import com.university.nn.kotlinbased.utils.Container
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FeedService
@Autowired
constructor(private val feedDao: FeedDao) {
    fun getFeeds(urls: List<String>): List<Feed> = feedDao.getFeeds(urls)
    fun searchFeeds(key: String): List<Container> = feedDao.searchFeeds(key)
}