package com.university.nn.kotlinbased.server.security

import com.university.nn.kotlinbased.db.repository.IUserClassRepository
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
open class XAuthUserDetailsService @Autowired constructor(private val userRepository: IUserClassRepository) : UserDetailsService {

    val LOGGER = Logger.getLogger(XAuthUserDetailsService::class.java)

    override fun loadUserByUsername(username: String): UserDetails {
        LOGGER.info(username)
        val user = userRepository.findByUsername(username) ?: throw Exception("User not found")
        LOGGER.info(user)
        return XAuthUserDetails(user.username,
                user.password,
                true, true, true, true,
                user.roles.map { SimpleGrantedAuthority(it.name) }.toMutableList())
    }

}