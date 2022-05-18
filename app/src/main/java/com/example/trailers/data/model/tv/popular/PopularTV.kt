package com.example.trailers.data.model.tv.popular

data class PopularTV(
    val page: Int?,
    val results: List<Result>?,
    val total_pages: Int?,
    val total_results: Int?
)