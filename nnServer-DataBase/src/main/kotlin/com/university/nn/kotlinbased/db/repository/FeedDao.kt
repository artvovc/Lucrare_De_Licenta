package com.university.nn.kotlinbased.db.repository

import com.university.nn.kotlinbased.db.response.ResponseFeed
import com.university.nn.kotlinbased.utils.Container

interface FeedDao {
    fun getFeeds(urls: List<String>): List<ResponseFeed>
    fun searchFeeds(key: String): List<Container>
}