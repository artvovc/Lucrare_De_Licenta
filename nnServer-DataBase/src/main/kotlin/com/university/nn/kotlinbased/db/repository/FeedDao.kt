package com.university.nn.kotlinbased.db.repository

import com.university.nn.kotlinbased.db.model.Feed
import com.university.nn.kotlinbased.utils.Container

interface FeedDao {
    fun getFeeds(flinks: List<String>): List<Feed>
    fun searchFeeds(key: String): List<Container>
}