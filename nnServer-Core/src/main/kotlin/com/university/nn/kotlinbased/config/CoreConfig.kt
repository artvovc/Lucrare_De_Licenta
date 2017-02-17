package com.university.nn.kotlinbased.config

import com.university.nn.kotlinbased.db.config.MongodbConfig
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import


@Configuration
@ComponentScan(value = "com.university.nn.kotlinbased.services")
@Import(MongodbConfig::class)
open class CoreConfig {

}