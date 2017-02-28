package com.university.nn.kotlinbased.db.request

class RequestSearch {
    var key: String = ""
        set(value) { field = value.toLowerCase() }
}