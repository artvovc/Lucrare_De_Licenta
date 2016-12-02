package com.university.nn.kotlinbased.server.controller

import com.university.nn.kotlinbased.db.model.UserClass
import com.university.nn.kotlinbased.db.repository.IUserClassRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.gridfs.GridFsOperations
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus.OK
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import java.io.ByteArrayInputStream
import java.nio.charset.Charset
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Controller
class FirstController
@Autowired
constructor(private val iuscr: IUserClassRepository,
            private val gridFsOperations: GridFsOperations) {

    @GetMapping(path = arrayOf("/hello"), produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun sayHello(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse): HttpEntity<Any?> {
        return ResponseEntity("adasfqweqwrqwr", OK)
    }

    @GetMapping(path = arrayOf("/bye"), produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun seyBye(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse): HttpEntity<Any?> {
        val user = UserClass()
        user.firstname = "asdasda"
        user.lastname = "123123"
        iuscr.save(user)
        gridFsOperations.store(ByteArrayInputStream("asd".toByteArray(Charset.defaultCharset())), "superb")
        return ResponseEntity("ffffffffffffffd", OK)
    }

}