package com.university.nn.kotlinbased.server.controller

import com.university.nn.kotlinbased.db.request.RequestFeeds
import com.university.nn.kotlinbased.db.request.RequestSearch
import com.university.nn.kotlinbased.db.response.Pagination
import com.university.nn.kotlinbased.services.FeedService
import com.university.nn.kotlinbased.utils.Container
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

    @PostMapping(path = arrayOf("/search"))
    fun searchFeeds(@ModelAttribute requestSearch: RequestSearch, model: Model): ModelAndView {

        model.addAttribute("requestSearch", RequestSearch())
        model.addAttribute("container", feedService.searchFeeds(requestSearch.key))

        return ModelAndView("index")
    }

    //TODO     http://stackoverflow.com/questions/22682566/spring-display-image-in-jsp-from-mongodb
    @GetMapping(path = arrayOf("/"))
    fun getText(model: Model): ModelAndView {

        model.addAttribute("requestSearch", RequestSearch())
        model.addAttribute("container", null)

        return ModelAndView("index")
    }
}