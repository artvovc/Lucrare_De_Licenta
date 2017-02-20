package com.university.nn.kotlinbased.server.request

import java.io.Serializable
import java.util.*

open class RequestFeeds : Serializable {
    var urls: MutableList<String> = ArrayList()
}