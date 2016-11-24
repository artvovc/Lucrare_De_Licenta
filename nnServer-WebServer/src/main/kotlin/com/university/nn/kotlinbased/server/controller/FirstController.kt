package com.university.nn.kotlinbased.server.controller

import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus.OK
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
class FirstController {

    @GetMapping(path = arrayOf("/hello"), produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun sayHello(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse): HttpEntity<Any?> {
        return ResponseEntity("adasfqweqwrqwr", OK)
    }

    @GetMapping(path = arrayOf("/bye"), produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun seyBye(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse): HttpEntity<Any?> {
        return ResponseEntity("ffffffffffffffd", OK)
    }

}