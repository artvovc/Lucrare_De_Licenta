package com.university.nn.kotlinbased.db.request

import java.util.*

open class RequestFeeds {
    var urls: List<String> = ArrayList()
        set(value) {
            field = value.filter { item -> item.contains("^https?://.*".toRegex()) }
        }
    var pageSize: Int = 5
        set(value) {
            field = if (value > 5) value else 5
        }
    var page: Int = 0
        set(value) {
            field = if (value >= 0) value else 0
        }
}