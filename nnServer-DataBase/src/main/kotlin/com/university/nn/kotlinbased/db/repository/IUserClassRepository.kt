package com.university.nn.kotlinbased.db.repository

import com.university.nn.kotlinbased.db.model.NNUser
import org.springframework.data.mongodb.repository.MongoRepository

interface IUserClassRepository : MongoRepository<NNUser, String> {
    fun findByUsername(username: String): NNUser?
}