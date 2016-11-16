package com.university.nn.kotlinbased.server.config

import org.springframework.context.annotation.Bean
import springfox.documentation.swagger2.annotations.EnableSwagger2
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration

@EnableSwagger2
open class Swagger2Config{
    @Bean
    open fun swagger2():Swagger2DocumentationConfiguration = Swagger2DocumentationConfiguration()
}
