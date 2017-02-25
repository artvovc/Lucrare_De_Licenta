package com.university.nn.kotlinbased.server.controller

import com.university.nn.kotlinbased.db.request.RequestFeeds
import com.university.nn.kotlinbased.db.request.RequestSearch
import com.university.nn.kotlinbased.db.response.Pagination
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
constructor(private val feedService: FeedService) {

    internal var logger = Logger.getLogger(FeedController::class.java)

    @PostMapping(
            path = arrayOf("/"),
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun getFeeds(@RequestBody requestFeeds: RequestFeeds): HttpEntity<Any> {
        if (requestFeeds.urls.size == 0) return ResponseEntity(BAD_REQUEST)
        return ResponseEntity(Pagination.paginate(requestFeeds, feedService.getFeeds(requestFeeds.urls)), OK)
    }

    @PostMapping(
            path = arrayOf("/search"),
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun searchFeeds(@RequestBody requestSearch: RequestSearch): HttpEntity<Any> {
        if (requestSearch.key.isEmpty())
            return ResponseEntity(BAD_REQUEST)
        val response = feedService.searchFeeds(requestSearch.key)
        if (response.isEmpty()) throw Exception() else return ResponseEntity(response, OK)
    }

}
