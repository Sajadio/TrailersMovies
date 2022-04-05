package com.example.movie.data.model.tv.trend

data class Trending(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)