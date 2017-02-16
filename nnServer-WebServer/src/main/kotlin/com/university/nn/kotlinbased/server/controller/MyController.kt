package com.university.nn.kotlinbased.server.controller

import com.university.nn.kotlinbased.db.model.NNUser
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

/**
 * Created by win on 2/16/17.
 */
@Controller
class MyController {

    @PostMapping(path = arrayOf("/pet"))
    fun add(@ModelAttribute nnUser: NNUser): HttpEntity<String> {
        println(nnUser)
        return ResponseEntity("Yes", HttpStatus.OK)
    }
}