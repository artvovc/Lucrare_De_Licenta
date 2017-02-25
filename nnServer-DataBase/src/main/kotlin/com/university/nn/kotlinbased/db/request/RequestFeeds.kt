package com.university.nn.kotlinbased.db.request

import java.io.Serializable
import java.util.*

open class RequestFeeds() : Serializable {
    var urls: MutableList<String> = ArrayList()
    var pageSize: Int = 5
    var page: Int = 0
}