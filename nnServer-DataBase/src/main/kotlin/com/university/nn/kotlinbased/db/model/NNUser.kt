package com.university.nn.kotlinbased.db.model

import com.university.nn.kotlinbased.db.model.enums.Role
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
data class NNUser(@Id
                  var id: String?,
                  var firstname: String?,
                  var lastname: String?,
                  var username: String,
                  var password: String,
                  var roles: MutableCollection<Role>
) {
    constructor(username: String, password: String, roles: MutableCollection<Role>) :
            this(id = null, firstname = null, lastname = null, username = username, password = password, roles = roles)
    constructor(username: String, password: String) :
            this(id = null, firstname = null, lastname = null, username = username, password = password, roles = mutableListOf())
    constructor() :
            this(id = null, firstname = null, lastname = null, username = "", password = "", roles = mutableListOf())

}