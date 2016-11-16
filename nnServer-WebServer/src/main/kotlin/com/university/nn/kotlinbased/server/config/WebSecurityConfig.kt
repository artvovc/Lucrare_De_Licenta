//package com.university.nn.kotlinbased.server.config
//
//import com.starling.soft.socialapp.kotlinbased.db.config.HibernateConfig
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.context.annotation.ComponentScan
//import org.springframework.context.annotation.Configuration
//import org.springframework.context.annotation.Import
//import org.springframework.http.HttpMethod
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
//import org.springframework.security.core.userdetails.UserDetailsService
//import javax.annotation.Resource
//
//
////TODO needed implementation
//@Configuration
//@EnableWebSecurity
//@ComponentScan(basePackages = arrayOf("com.starling.soft.socialapp.kotlinbased.server.security.service"))
//@Import(HibernateConfig::class)
//open class WebSecurityConfig : WebSecurityConfigurerAdapter() {
//
//
//    @Autowired
//    override fun configure(auth: AuthenticationManagerBuilder?) {
////        auth
////                ?.userDetailsService(userDetailsService)
//////                ?.passwordEncoder(ShaPasswordEncoder())
//        auth
//                ?.inMemoryAuthentication()
//                ?.withUser("admin")?.password("nimda")?.roles("ADMIN")
//    }
//
//    override fun configure(http: HttpSecurity?) {
//
////                ?.authenticationProvider()
//        http
//                ?.authorizeRequests()
//                ?.antMatchers("/*")                       ?.permitAll()
//                ?.antMatchers(HttpMethod.GET, "/user/*")  ?.permitAll()
//
//        http
//                ?.csrf()?.disable()
//    }
//
//    @Resource(name = "authService")
//    private var userDetailsService: UserDetailsService? = null
//
////    @Bean
////    override fun userDetailsService(): UserDetailsService? = AuthService()
//}