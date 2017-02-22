package com.university.nn.kotlinbased.server.controller

import com.university.nn.kotlinbased.db.repository.impl.FeedDaoImpl
import com.university.nn.kotlinbased.server.request.RequestFeeds
import com.university.nn.kotlinbased.server.request.RequestSearch
import com.university.nn.kotlinbased.server.response.ListDto
import com.university.nn.kotlinbased.server.response.ResponseFeed
import com.university.nn.kotlinbased.services.FeedService
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.OK
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class FeedController
@Autowired
constructor(
        private val feedService: FeedService) {

    internal var logger = Logger.getLogger(FeedController::class.java)
    @PostMapping(path = arrayOf("/"), produces = arrayOf(MediaType.APPLICATION_JSON_VALUE), consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun getFeeds(@RequestBody requestFeeds: RequestFeeds): HttpEntity<Any> {
        if (requestFeeds.urls.size == 0)
            return ResponseEntity(BAD_REQUEST)
        val listDto = ListDto<ResponseFeed>()
        val list = feedService.getFeeds(requestFeeds.urls).flatMap { syndFeed ->
            syndFeed.entries.map { entry ->
                ResponseFeed(entry.title, entry.author, entry.link, entry.description.value, entry.publishedDate)
            }
        }
        val size = list.size

//        listDto.list = list.drop(requestFeeds.page*requestFeeds.pageSize).dropLast()

        return ResponseEntity(listDto, OK)
    }

    @PostMapping(path = arrayOf("/search"), produces = arrayOf(MediaType.APPLICATION_JSON_VALUE), consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun searchFeeds(@RequestBody requestSearch: RequestSearch): HttpEntity<Any> {
        if (requestSearch.key.isEmpty())
            return ResponseEntity(BAD_REQUEST)
        val response = feedService.searchFeeds(requestSearch.key)
        if (response.isEmpty())
            throw Exception()
        return ResponseEntity(response, OK)
    }

}
