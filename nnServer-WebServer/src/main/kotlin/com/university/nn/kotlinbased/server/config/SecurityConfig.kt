package com.university.nn.kotlinbased.server.config

import com.university.nn.kotlinbased.server.security.XAuthAuthenticationProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
@ComponentScan("com.university.nn.kotlinbased.server.security")
open class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    var xAuthAuthenticationProvider: XAuthAuthenticationProvider? = null

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.authenticationProvider(xAuthAuthenticationProvider)
    }

    override fun configure(http: HttpSecurity?) {
        http?.authorizeRequests()?.anyRequest()?.authenticated()
//                ?.authorizeRequests()
//                ?.antMatchers("/hello")?.permitAll()
//                ?.antMatchers("/bye")?.permitAll()
//                ?.anyRequest()?.authenticated()
                ?.and()
                ?.httpBasic()

        http
                ?.csrf()?.disable()
    }

    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

//    override fun userDetailsServiceBean(): UserDetailsService {
//        return XAuthUserDetailsService(userRopository!!)
//    }

}