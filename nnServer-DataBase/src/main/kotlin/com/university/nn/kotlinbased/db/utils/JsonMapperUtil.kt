package com.university.nn.kotlinbased.db.utils

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

/**
 * Created by dsdmsa on 2/25/17.
 */

fun Any.toJson(): String {
    val mapper = jacksonObjectMapper()
    return mapper.writeValueAsString(this)
}




inline fun <reified T : Any> String.toClass(): T {
    val mapper = jacksonObjectMapper()
    return mapper.readValue(this, T::class.java)
}

