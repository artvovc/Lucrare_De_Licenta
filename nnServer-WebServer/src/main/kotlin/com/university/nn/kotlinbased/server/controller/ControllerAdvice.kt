package com.university.nn.kotlinbased.server.controller

import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
open class ControllerAdvice {

    @ExceptionHandler(Exception::class)
    fun exception(e: Exception): HttpEntity<Any> {
        println(e)
        return ResponseEntity("""{"error":"feed was not found"}""", BAD_REQUEST)
    }
}