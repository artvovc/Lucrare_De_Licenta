package com.university.nn.kotlinbased.server.config

import com.university.nn.kotlinbased.config.CoreConfig
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(CoreConfig::class)
open class AppContextConfig
