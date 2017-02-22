package com.university.nn.kotlinbased.server.controller

import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.io.StringWriter
import java.io.PrintWriter




@ControllerAdvice
open class ControllerAdvice {

    @ExceptionHandler(Exception::class)
    fun exception(e: Exception): HttpEntity<Any> {
        println(getStackTrace(e))
        return ResponseEntity("""{"error":"feed was not found", "e.message": "${e.message}", "e.trace":"${getStackTrace(e)}" }""", OK)
    }

    fun getStackTrace(throwable: Exception): String {


        val elements = throwable.stackTrace
        var i = 0
        val n = elements.size

        var err = ""

        while (i < n) {
            err += (elements[i].fileName
                    + ":" + elements[i].lineNumber
                    + ">> "
                    + elements[i].methodName + "()")
            i++
        }


        return err
    }
}