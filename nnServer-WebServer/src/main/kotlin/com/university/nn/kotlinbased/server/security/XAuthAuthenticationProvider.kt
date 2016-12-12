package com.university.nn.kotlinbased.server.security

import com.university.nn.kotlinbased.db.repository.IUserClassRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component

@Component
open class XAuthAuthenticationProvider @Autowired constructor(private val userRepository: IUserClassRepository) : AuthenticationProvider {
    override fun supports(authentication: Class<*>?): Boolean = authentication!! == UsernamePasswordAuthenticationToken::class.java
    override fun authenticate(authentication: Authentication?): Authentication {
        val user = userRepository.findByUsername(authentication!!.name)
        val userDetails = XAuthUserDetails(user.username!!,
                user.password!!,
                true, true, true, true,
                user.roles!!.map { SimpleGrantedAuthority(it.name) }.toMutableList())
        return UsernamePasswordAuthenticationToken(userDetails, userDetails.password, userDetails.authorities)
    }
}