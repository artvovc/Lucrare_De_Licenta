package com.university.nn.kotlinbased.db.config

import com.mongodb.MongoClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.data.mongodb.MongoDbFactory
import org.springframework.data.mongodb.config.AbstractMongoConfiguration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoDbFactory
import org.springframework.data.mongodb.gridfs.GridFsTemplate
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories


@Configuration
@PropertySource(value = *arrayOf("classpath:mongodb.properties"))
@EnableMongoRepositories(value = "com.university.nn.kotlinbased.db.repository")
open class MongodbConfig
@Autowired
constructor(private val environment: Environment) : AbstractMongoConfiguration() {

    override fun getDatabaseName(): String = environment.getRequiredProperty("mongo.db")


    override fun mongoDbFactory(): MongoDbFactory = SimpleMongoDbFactory(
            mongo(),
            environment.getRequiredProperty("mongo.db"))

    @Bean(name = arrayOf("mongoTemplate"))
    override fun mongoTemplate(): MongoTemplate = MongoTemplate(mongoDbFactory())

    override fun mongo(): MongoClient = MongoClient(
            environment.getRequiredProperty("mongo.host"),
            Integer.parseInt(environment.getRequiredProperty("mongo.port")))

    @Bean
    open fun gridFsTemplate(): GridFsTemplate = GridFsTemplate(mongoDbFactory(), mappingMongoConverter())

}