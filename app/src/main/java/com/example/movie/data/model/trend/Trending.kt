package com.example.movie.data.model.trend

import com.example.movie.utils.ParentListAdapter

data class Trending(
    val page: Int?,
    val results: List<Result>?,
    val total_pages: Int?,
    val total_results: Int?
):ParentListAdapter {
    override val ids = page
}