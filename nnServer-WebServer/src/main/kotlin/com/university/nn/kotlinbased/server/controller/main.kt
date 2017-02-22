package com.university.nn.kotlinbased.server.controller

fun main(args : Array<String>) {
    val list = listOf(1,2,3,4,5,6,7,8,9,0)

    val page = 1

    val pagesize = 5

    val totalPages = list.size/pagesize

    val mod = list.size.mod(totalPages)

    val pageElements = page*pagesize

    list.drop(pageElements).dropLast(list.size-pagesize).forEach (::println)

    println("page=${page}  pageSize=${pagesize}  totalPages=${totalPages}")


    println("${list.size.mod(totalPages)}")

}