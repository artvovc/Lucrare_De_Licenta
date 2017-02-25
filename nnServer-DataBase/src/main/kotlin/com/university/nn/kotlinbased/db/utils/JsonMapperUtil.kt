package com.university.nn.kotlinbased.db.utils

/**
 * Created by dsdmsa on 2/25/17.
 */


import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.university.nn.kotlinbased.db.repository.impl.ListContainerWraper
import com.university.nn.kotlinbased.utils.Container
import redis.clients.jedis.Jedis


fun Any.toJson(): String {
    val mapper = jacksonObjectMapper()
    print(mapper.writeValueAsString(this))
    return mapper.writeValueAsString(this)
}


inline fun <reified T : Any> String?.toClass(): T? {
    if (this == null) return null
    val mapper = jacksonObjectMapper()
    return mapper.readValue(this, T::class.java)
}


fun main(args: Array<String>) {
    val jedis = Jedis("localhost")
    val key = "key"
    val ct = Container()
    ct.iconUrl = "asdasd"
    val containerList: ListContainerWraper = ListContainerWraper(mutableListOf(Container(), ct))
    jedis.set(key, containerList.toJson())
    val rcontainerList: ListContainerWraper? = jedis.get(key).toClass()
    print(rcontainerList)
}

