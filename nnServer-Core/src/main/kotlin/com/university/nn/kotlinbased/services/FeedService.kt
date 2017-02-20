package com.university.nn.kotlinbased.services

import com.rometools.rome.feed.synd.SyndFeed
import com.university.nn.kotlinbased.db.repository.FeedDao
import com.university.nn.kotlinbased.utils.Container
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FeedService
@Autowired
constructor(private val feedDao: FeedDao){
    fun getFeeds(urls:List<String>): List<SyndFeed> = feedDao.getFeeds(urls)

    fun searchFeed(key: String): List<Container> = feedDao.searchFeed(key)
}