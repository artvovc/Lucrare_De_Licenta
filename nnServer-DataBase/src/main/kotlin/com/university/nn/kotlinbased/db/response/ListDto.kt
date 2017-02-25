package com.university.nn.kotlinbased.db.response

import java.io.Serializable
import java.util.*

open class ListDto<T> : Serializable {
     var page: Int = 0
     var pageSize: Int = 0
     var totalPages: Int = 0
     var list: List<T> = ArrayList()
}
