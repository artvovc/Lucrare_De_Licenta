package com.university.nn.kotlinbased.server.security

import com.university.nn.kotlinbased.db.repository.INNUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
open class XAuthUserDetailsService @Autowired constructor(private val userRepository: INNUserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username) ?: throw Exception("User not found")
        return XAuthUserDetails(user.username,
                user.password,
                true, true, true, true,
                user.roles.map { SimpleGrantedAuthority(it.name) }.toMutableList())
    }

}