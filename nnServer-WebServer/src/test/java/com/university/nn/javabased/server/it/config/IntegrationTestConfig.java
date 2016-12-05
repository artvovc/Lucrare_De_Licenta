package com.university.nn.javabased.server.it.config;

import com.university.nn.kotlinbased.server.config.AppContextConfig;
import com.university.nn.kotlinbased.server.config.DispatcherConfig;
import com.university.nn.kotlinbased.server.config.SecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Artemie on 03.12.2016.
 */
@Configuration
@Import(value = {AppContextConfig.class, SecurityConfig.class, DispatcherConfig.class})
public class IntegrationTestConfig {
}
