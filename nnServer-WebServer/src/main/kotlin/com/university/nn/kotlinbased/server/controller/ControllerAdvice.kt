package com.university.nn.kotlinbased.server.controller

import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
open class ControllerAdvice {

    @ExceptionHandler(Exception::class)
    fun exception(e: Exception): HttpEntity<Any> {
        return ResponseEntity("""{"error":"feed was not found"}""", OK)
    }
}