package com.university.nn.kotlinbased.server.it.config

import com.university.nn.kotlinbased.server.config.AppContextConfig
import com.university.nn.kotlinbased.server.config.DispatcherConfig
import com.university.nn.kotlinbased.server.config.SecurityConfig
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

/**
 * Created by win on 12/2/16.
 */
@Configuration
@Import(value = *arrayOf(AppContextConfig::class, DispatcherConfig::class, SecurityConfig::class))
open class IntegrationTestConfig
