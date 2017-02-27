package com.university.nn.kotlinbased.db.model

import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class Feed(
        var feedLink: String,
        var title: String?,
        var author: String?,
        var link: String?,
        var description: String?,
        var createdDate: Date?
){
        @Indexed(expireAfterSeconds = 10)
        var expireTime: Date = Date()
}