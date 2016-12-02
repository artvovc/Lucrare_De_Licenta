package com.university.nn.kotlinbased.db.repository

import com.university.nn.kotlinbased.db.model.UserClass
import org.springframework.data.mongodb.repository.MongoRepository

interface IUserClassRepository : MongoRepository<UserClass, String> {
}