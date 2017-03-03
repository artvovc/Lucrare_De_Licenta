package com.university.nn.kotlinbased.db.model

import org.springframework.data.mongodb.core.mapping.Document

/**
 * Created by pc on 3/3/17.
 */
@Document(collection = "feedDatas")
open class FeedData(
        open val url: String? = null,
        open val tags:List<String> = arrayListOf(),
        open val name: String? = null,
        open val image: String? = null
)