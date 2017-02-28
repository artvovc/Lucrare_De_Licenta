package com.university.nn.kotlinbased.db.repository

import com.university.nn.kotlinbased.db.model.Item
import com.university.nn.kotlinbased.utils.Container

interface ItemDao {
    fun getItems(flinks: List<String>): List<Item>
    fun searchItems(key: String): Container
}