package com.example.trailers.data.model.movie.trend

data class TrendMovie(
    val page: Int?,
    val results: List<TrendResult>?,
    val total_pages: Int?,
    val total_results: Int?
)