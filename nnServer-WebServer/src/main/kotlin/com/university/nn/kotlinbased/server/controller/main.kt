package com.university.nn.kotlinbased.server.controller

fun main(args : Array<String>) {
    val list = listOf(1,2,3,4,5,6,7,8,9)

    val page = 4123
    val pageSize = 3

    var totalPages = list.size/pageSize
    totalPages = if(list.size.mod(pageSize)!=0) totalPages+1 else totalPages

    val pageElementsToDrop = page*pageSize
    var pageElementsToDropLast = list.size-pageSize-pageElementsToDrop
    pageElementsToDropLast = if (pageElementsToDropLast>0) pageElementsToDropLast else 0

    list
            .drop(pageElementsToDrop)
            .dropLast(pageElementsToDropLast)
            .forEach (::println)

    println("page=${page}  pageSize=${pageSize}  totalPages=${totalPages}")


    println("${list.size.mod(totalPages)}")

}