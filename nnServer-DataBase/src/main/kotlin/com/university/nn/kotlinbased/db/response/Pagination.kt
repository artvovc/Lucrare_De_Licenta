package com.university.nn.kotlinbased.db.response

import com.university.nn.kotlinbased.db.model.Feed
import com.university.nn.kotlinbased.db.request.RequestFeeds

object Pagination {
    fun paginate(requestFeeds: RequestFeeds, list: List<Feed>): ListDto<Feed> {
        val listDto = ListDto<Feed>()
        var totalPages = list.size / requestFeeds.pageSize
        totalPages = if (list.size.mod(requestFeeds.pageSize) != 0) totalPages + 1 else totalPages
        listDto.totalPages = totalPages

        val pageElementsToDrop = requestFeeds.page * requestFeeds.pageSize
        var pageElementsToDropLast = list.size - requestFeeds.pageSize - pageElementsToDrop
        pageElementsToDropLast = if (pageElementsToDropLast > 0) pageElementsToDropLast else 0

        listDto.list = list
                .drop(pageElementsToDrop)
                .dropLast(pageElementsToDropLast)

        listDto.page = requestFeeds.page
        listDto.pageSize = requestFeeds.pageSize

        return listDto
    }
}
