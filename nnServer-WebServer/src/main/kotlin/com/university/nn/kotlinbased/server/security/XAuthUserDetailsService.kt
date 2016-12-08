//package com.university.nn.kotlinbased.server.security
//
//import com.university.nn.kotlinbased.db.repository.IUserClassRepository
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.security.core.userdetails.UserDetails
//import org.springframework.security.core.userdetails.UserDetailsService
//import org.springframework.stereotype.Service
//
//@Service
//open class XAuthUserDetailsService @Autowired constructor(private val userRepository: IUserClassRepository) : UserDetailsService {
//    override fun loadUserByUsername(username: String?): UserDetails {
//        var user = userRepository.findByUsername(username!!)
//        var blo = XAuthUserDetails()
//        return blo
//    }
//
//}