package com.example.trailers.data.model.search

data class Search(
    val page: Int?,
    val results: List<Result>?,
    val total_pages: Int?,
    val total_results: Int?
)