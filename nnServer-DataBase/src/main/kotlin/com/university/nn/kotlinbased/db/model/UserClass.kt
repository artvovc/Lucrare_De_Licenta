package com.university.nn.kotlinbased.db.model

import org.springframework.data.annotation.Id

class UserClass {
    @Id
    var id: String? = null
    var firstname: String? = null
    var lastname: String? = null
}