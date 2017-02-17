package com.university.nn.kotlinbased.server.response

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import java.util.*
import kotlin.collections.List

@ApiModel(value = "ListDto<T>")
open class ListDto<T> : Serializable {
    @ApiModelProperty(name = "list", dataType = "List<T>", required = true)
    private val list: MutableList<T>

    init {
        list = ArrayList<T>()
    }

    fun getList(): List<T> {
        return list
    }

    fun setList(set: Set<T>) {
        this.list.addAll(set)
    }

    fun setElement(elem : T) {
        this.list.add(elem)
    }
}
