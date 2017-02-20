package com.university.nn.kotlinbased.server.controller

import com.university.nn.kotlinbased.db.model.NNUser
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class WebController {

    @PostMapping(path = arrayOf("/pet"))
    fun add(@ModelAttribute nnUser: NNUser): HttpEntity<String> {
        println(nnUser)
        return ResponseEntity("Yes", HttpStatus.OK)
    }

    //TODO     http://stackoverflow.com/questions/22682566/spring-display-image-in-jsp-from-mongodb
    @GetMapping(path = arrayOf("/"))
    fun getText(): ModelAndView {
        return ModelAndView("index", "command", NNUser(username = "MyName", password = "asdasd"))
    }
}