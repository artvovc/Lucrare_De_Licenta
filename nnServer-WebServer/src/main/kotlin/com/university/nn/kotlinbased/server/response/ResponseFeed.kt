package com.university.nn.kotlinbased.server.response

import java.io.Serializable
import java.util.*

open class ResponseFeed(
        val title:String?,
        val author:String?,
        val link:String?,
        val description:String?,
        val createdDate: Date?
        ) : Serializable