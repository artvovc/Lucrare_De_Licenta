package com.university.nn.kotlinbased.server.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(com.university.nn.kotlinbased.db.config.MongodbConfig::class)
open class AppContextConfig
