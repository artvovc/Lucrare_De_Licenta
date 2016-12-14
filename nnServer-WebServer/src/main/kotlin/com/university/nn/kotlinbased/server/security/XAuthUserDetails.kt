package com.university.nn.kotlinbased.server.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class XAuthUserDetails
constructor(
        private val username: String,
        private val password: String,
        private val isCredentialsNonExpired: Boolean,
        private val isAccountNonExpired: Boolean,
        private val isAccountNonLocked: Boolean,
        private val isEnabled: Boolean,
        private val roles: MutableCollection<SimpleGrantedAuthority>
) : UserDetails {
    override fun getUsername(): String = this.username
    override fun getPassword(): String = this.password
    override fun isCredentialsNonExpired(): Boolean = this.isCredentialsNonExpired
    override fun isAccountNonExpired(): Boolean = this.isAccountNonExpired
    override fun isAccountNonLocked(): Boolean = this.isAccountNonLocked
    override fun isEnabled(): Boolean = this.isEnabled
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = this.roles
}