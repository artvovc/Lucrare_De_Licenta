package com.university.nn.kotlinbased.services

import com.rometools.rome.feed.synd.SyndFeed
import com.university.nn.kotlinbased.db.repository.FeedDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FeedService
@Autowired
constructor(private val feedDao: FeedDao){
    fun getFeed(url:String): SyndFeed = feedDao.getFeed(url)

    fun searchFeed(key: String): List<String> = feedDao.searchFeed(key)
}