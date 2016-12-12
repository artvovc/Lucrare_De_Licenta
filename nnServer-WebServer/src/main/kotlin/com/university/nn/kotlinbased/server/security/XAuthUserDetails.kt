package com.university.nn.kotlinbased.server.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
open class XAuthUserDetails : UserDetails {

    private val username: String = ""
    private val password: String = ""
    private val isCredentialsNonExpired: Boolean = true
    private val isAccountNonExpired: Boolean = true
    private val isAccountNonLocked: Boolean = true
    private val isEnabled: Boolean = true
    private val roles: MutableCollection<SimpleGrantedAuthority> = null!!

    override fun getUsername(): String = this.username

    override fun getPassword(): String = this.password

    override fun isCredentialsNonExpired(): Boolean = this.isCredentialsNonExpired

    override fun isAccountNonExpired(): Boolean = this.isAccountNonExpired

    override fun isAccountNonLocked(): Boolean = this.isAccountNonLocked

    override fun isEnabled(): Boolean = this.isEnabled

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = this.roles

}