package com.university.nn.kotlinbased.db.model

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "feeds")
data class Feed(
    var key: String = "",
    var iconUrl: String? = null,
    var feeds: List<String> = arrayListOf()
)