package com.university.nn.kotlinbased.server.controller

import com.university.nn.kotlinbased.db.model.NNUser
import com.university.nn.kotlinbased.db.repository.INNUserRepository
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
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.xml.ws.Response

@Controller
class WelloController
@Autowired
constructor(private val iuscr: INNUserRepository,
            private val gridFsOperations: GridFsOperations) {

//    @GetMapping(path = arrayOf("/hello"), produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
//    fun sayHello(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse): HttpEntity<Any> {
//        val user = SecurityContextHolder.getContext().authentication.principal as XAuthUserDetails
//        return ResponseEntity(user, OK)
//    }
//
//    @GetMapping(path = arrayOf("/bye"), produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
//    fun seyBye(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse): HttpEntity<Any> {
//        val user = SecurityContextHolder.getContext().authentication.principal as XAuthUserDetails
////        val user = NNUser()
////        user.firstname = "asd"
////        user.lastname = "asd"
////        user.username = "asd"
////        user.password = "asd"
////        user.roles = mutableListOf(Role.ADMIN, Role.USER)
////        iuscr.save(user)
//        val user1 = iuscr.findByUsername(user.username)
////        gridFsOperations.store(ByteArrayInputStream("asd".toByteArray(Charset.defaultCharset())), "superb")
//        return ResponseEntity(user1, OK)
//    }


    //TODO     http://stackoverflow.com/questions/22682566/spring-display-image-in-jsp-from-mongodb
    @GetMapping(path = arrayOf("/"))
    fun getText(): ModelAndView {
        return ModelAndView("index","command", NNUser(username = "MyName", password = "asdasd"))
    }

}