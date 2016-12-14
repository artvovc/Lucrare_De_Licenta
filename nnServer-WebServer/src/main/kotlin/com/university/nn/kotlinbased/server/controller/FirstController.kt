package com.university.nn.kotlinbased.server.controller

import com.university.nn.kotlinbased.db.model.NNUser
import com.university.nn.kotlinbased.db.repository.IUserClassRepository
import com.university.nn.kotlinbased.server.security.XAuthUserDetails
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.gridfs.GridFsOperations
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus.OK
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
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
    fun sayHello(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse): HttpEntity<Any> {
        val user = SecurityContextHolder.getContext().authentication.principal as XAuthUserDetails
        return ResponseEntity(user, OK)
    }

    @GetMapping(path = arrayOf("/bye"), produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun seyBye(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse): HttpEntity<Any> {
//        val user = UserClass(username = "asd", password = "asd", roles = mutableListOf(Role.ADMIN, Role.USER))
        val user = NNUser()

//        user.firstname = "asd"
//        user.lastname = "asd"
//        user.username = "asd"
//        user.password = "asd"
//        user.roles = mutableListOf(Role.ADMIN, Role.USER)
        iuscr.save(user)
        val user1 = iuscr.findByUsername("asd")
        gridFsOperations.store(ByteArrayInputStream("asd".toByteArray(Charset.defaultCharset())), "superb")
        return ResponseEntity(user1, OK)
    }

    @PostMapping(path = arrayOf("/post"), consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun postUser(@RequestBody nnUser: NNUser): HttpEntity<Any> {
        iuscr.insert(nnUser)
        return ResponseEntity("ok", OK)
    }

}