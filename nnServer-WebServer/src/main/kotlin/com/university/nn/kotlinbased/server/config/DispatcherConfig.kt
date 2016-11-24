package com.university.nn.kotlinbased.server.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.view.InternalResourceViewResolver


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = arrayOf("com.university.nn.kotlinbased.server.controller"))
@Import(AppContextConfig::class, Swagger2Config::class)
open class DispatcherConfig : WebMvcConfigurerAdapter() {
    override fun addResourceHandlers(registry: ResourceHandlerRegistry?) {
        registry!!.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/")

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
    }

    @Bean(name = arrayOf("viewResolver"))
    open fun getViewResolver(): InternalResourceViewResolver {
        val viewResolver = InternalResourceViewResolver()
        viewResolver.setPrefix("/WEB-INF/views/")
        viewResolver.setSuffix(".jsp")
        return viewResolver
    }

//    override fun addCorsMappings(registry: CorsRegistry?) {
//        registry
//                ?.addMapping("/**")
//                ?.allowCredentials(true)
//                ?.allowedOrigins("*")
//                ?.allowedHeaders("*")
//                ?.allowedMethods("*")
//                ?.maxAge(2400)
//    }
}
