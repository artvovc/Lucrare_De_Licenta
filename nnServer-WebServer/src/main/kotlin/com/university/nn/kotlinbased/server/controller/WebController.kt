package com.university.nn.kotlinbased.server.controller

import com.university.nn.kotlinbased.db.request.RequestFeeds
import com.university.nn.kotlinbased.db.request.RequestSearch
import com.university.nn.kotlinbased.db.response.Pagination
import com.university.nn.kotlinbased.services.FeedService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class WebController
@Autowired
constructor(private val feedService: FeedService) {

    @PostMapping(path = arrayOf("/"))
    fun getFeeds(@ModelAttribute requestFeeds: RequestFeeds): HttpEntity<Any> {
        if (requestFeeds.urls.isEmpty()) return ResponseEntity(HttpStatus.BAD_REQUEST)
        return ResponseEntity(Pagination.paginate(requestFeeds, feedService.getFeeds(requestFeeds.urls)), HttpStatus.OK)
    }

    @PostMapping(path = arrayOf("/search"))
    fun searchFeeds(@ModelAttribute requestSearch: RequestSearch): HttpEntity<Any> {
        if (requestSearch.key.isEmpty()) return ResponseEntity(HttpStatus.BAD_REQUEST)
        val response = feedService.searchFeeds(requestSearch.key)
        if (response.feeds.isEmpty()) throw Exception() else return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping(path = arrayOf("/pet"))
    fun add(@ModelAttribute nnUser: RequestSearch): HttpEntity<String> {
        println(nnUser)
        return ResponseEntity("Yes", HttpStatus.OK)
    }

    //TODO     http://stackoverflow.com/questions/22682566/spring-display-image-in-jsp-from-mongodb
    @GetMapping(path = arrayOf("/"))
    fun getText(model: Model): ModelAndView {

        model.addAttribute("requestSearch", RequestSearch())

        return ModelAndView("index")
    }
}