package com.university.nn.kotlinbased.db.config

import com.mongodb.MongoClient
import org.mongeez.MongeezRunner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.core.io.ClassPathResource
import org.springframework.data.mongodb.MongoDbFactory
import org.springframework.data.mongodb.config.AbstractMongoConfiguration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoDbFactory
import org.springframework.data.mongodb.gridfs.GridFsTemplate
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.context.annotation.Bean
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.data.redis.serializer.GenericToStringSerializer




@Configuration
@PropertySource(value = *arrayOf("classpath:mongodb.properties","classpath:/redis.properties"))
@EnableMongoRepositories(value = "com.university.nn.kotlinbased.db.repository")
@ComponentScan(value = "com.university.nn.kotlinbased.db.repository.impl")
open class MongodbConfig
@Autowired
constructor(private val environment: Environment) : AbstractMongoConfiguration() {

    @Value("\${redis.host}") private val redisHost: String? = null
    @Value("\${redis.port}") private val redisPort: Int = 0

    @Bean
    fun propertySourcesPlaceholderConfigurer(): PropertySourcesPlaceholderConfigurer {
        return PropertySourcesPlaceholderConfigurer()
    }

    @Bean
    fun jedisConnectionFactory(): JedisConnectionFactory {
        val factory = JedisConnectionFactory()
        factory.hostName = redisHost
        factory.port = redisPort
        factory.usePool = true
        return factory
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        val template = RedisTemplate<String, Any>()
        template.connectionFactory = jedisConnectionFactory()
        template.keySerializer = StringRedisSerializer()
        template.hashValueSerializer = GenericToStringSerializer(Any::class.java)
        template.valueSerializer = GenericToStringSerializer(Any::class.java)
        return template
    }

    ////===============

    override fun getDatabaseName(): String = environment.getRequiredProperty("mongo.db")

    override fun mongoDbFactory(): MongoDbFactory = SimpleMongoDbFactory(mongo(), environment.getRequiredProperty("mongo.db"))

    @Bean(name = arrayOf("mongoTemplate"))
    override fun mongoTemplate(): MongoTemplate = MongoTemplate(mongoDbFactory())

    override fun mongo(): MongoClient = MongoClient(environment.getRequiredProperty("mongo.host"), Integer.parseInt(environment.getRequiredProperty("mongo.port")))

    @Bean
    open fun gridFsTemplate(): GridFsTemplate = GridFsTemplate(mongoDbFactory(), mappingMongoConverter())

    @Bean
    open fun mongeez(): MongeezRunner {
        val mongeez = MongeezRunner()
        mongeez.setMongo(mongo())
        mongeez.isExecuteEnabled = true
        mongeez.dbName = databaseName
        mongeez.setFile(ClassPathResource("mongeez.xml"))
        return mongeez
    }

}