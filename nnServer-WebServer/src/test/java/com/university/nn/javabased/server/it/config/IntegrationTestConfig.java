package com.university.nn.javabased.server.it.config;

import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import com.university.nn.kotlinbased.server.config.AppContextConfig;
import com.university.nn.kotlinbased.server.config.DispatcherConfig;
import com.university.nn.kotlinbased.server.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;

@Configuration
@Import(value = {AppContextConfig.class, SecurityConfig.class, DispatcherConfig.class})
public class IntegrationTestConfig {

    @Autowired
    private Environment environment;

    @Bean(name = "mongoRule")
    public MongoDbRule mongoDbRule() {
        return newMongoDbRule().defaultManagedMongoDb(
                environment.getRequiredProperty("mongo.db"),
                Integer.parseInt(environment.getRequiredProperty("mongo.port")));
    }
}
