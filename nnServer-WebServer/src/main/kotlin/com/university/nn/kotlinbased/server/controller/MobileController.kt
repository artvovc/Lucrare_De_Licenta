package com.university.nn.kotlinbased.server.controller

import com.university.nn.kotlinbased.db.repository.INNUserRepository
import com.university.nn.kotlinbased.db.request.RequestFeeds
import com.university.nn.kotlinbased.db.request.RequestSearch
import com.university.nn.kotlinbased.db.response.Pagination.paginate
import com.university.nn.kotlinbased.services.FeedService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.OK
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(path = arrayOf("/mo"))
class MobileController
@Autowired
constructor(private val feedService: FeedService, val innUserRepository: INNUserRepository) {

    @PostMapping(path = arrayOf("/"), produces = arrayOf(MediaType.APPLICATION_JSON_VALUE), consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun getFeeds(@RequestBody requestFeeds: RequestFeeds): HttpEntity<Any> {
        if (requestFeeds.urls.isEmpty()) throw Exception()
        return ResponseEntity(paginate(requestFeeds, feedService.getFeeds(requestFeeds.urls)), OK)
    }

    @PostMapping(path = arrayOf("/search"), produces = arrayOf(MediaType.APPLICATION_JSON_VALUE), consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun searchFeeds(@RequestBody requestSearch: RequestSearch): HttpEntity<Any> {
        if (requestSearch.key.isEmpty())
            return ResponseEntity(BAD_REQUEST)
        val response = feedService.searchFeeds(requestSearch.key)
        if (response.feeds.isEmpty()) throw Exception() else return ResponseEntity(response, OK)
    }
}
