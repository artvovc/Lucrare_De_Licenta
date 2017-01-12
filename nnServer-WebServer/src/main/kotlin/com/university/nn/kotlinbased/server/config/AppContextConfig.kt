package com.university.nn.kotlinbased.server.config

import com.university.nn.kotlinbased.db.config.MongodbConfig
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(MongodbConfig::class)
open class AppContextConfig {

}
