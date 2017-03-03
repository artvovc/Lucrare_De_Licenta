package com.university.nn.kotlinbased.server.controller

import com.university.nn.kotlinbased.db.request.RequestFeeds
import com.university.nn.kotlinbased.db.request.RequestSearch
import com.university.nn.kotlinbased.db.response.Pagination.paginate
import com.university.nn.kotlinbased.services.FeedService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

@Controller
class WebController
@Autowired
constructor(private val feedService: FeedService) {

    @GetMapping(path = arrayOf("/"))
    fun getText(model: Model): ModelAndView {

        model.addAttribute("requestSearch", RequestSearch())
        model.addAttribute("container", null)
        model.addAttribute("content", paginate(RequestFeeds(), feedService.getFeeds(listOf("https://4pda.ru/feed/"))))

        return ModelAndView("index")
    }

    @PostMapping(path = arrayOf("/search"))
    fun searchFeeds(@ModelAttribute requestSearch: RequestSearch, model: Model): ModelAndView {

        model.addAttribute("requestSearch", RequestSearch())
        model.addAttribute("container", feedService.searchFeeds(requestSearch.key))
        model.addAttribute("content", null)

        return ModelAndView("index")
    }

    @PostMapping(path = arrayOf("/content"))
    fun contentFeeds(@RequestParam("pageSize") pageSize: Int,
                     @RequestParam("page") page: Int,
                     @RequestParam("urls") urls: List<String>,
                     model: Model): ModelAndView {

        val requestFeeds = RequestFeeds()
        with(requestFeeds) {
            this.page = page
            this.pageSize = pageSize
            this.urls = urls
        }
        model.addAttribute("requestSearch", RequestSearch())
        model.addAttribute("container", null)
        if (requestFeeds.urls.isEmpty()) {
            model.addAttribute("content", paginate(requestFeeds, feedService.getFeeds(listOf("https://4pda.ru/feed/"))))
        } else
            model.addAttribute("content", paginate(requestFeeds, feedService.getFeeds(requestFeeds.urls)))

        return ModelAndView("index")
    }

}