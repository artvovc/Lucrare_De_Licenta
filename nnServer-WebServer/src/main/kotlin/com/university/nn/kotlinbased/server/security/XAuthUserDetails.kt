package com.university.nn.kotlinbased.server.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
open class XAuthUserDetails : UserDetails {
    override fun getUsername(): String = "asd"

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority("NOOB"), SimpleGrantedAuthority("MUDAK"))
    }

    override fun isEnabled(): Boolean = true

    override fun getPassword(): String = "asd"


}