package com.example.trailers.data.model.movie.playnow

data class MoviePlayNow(
    val dates: Dates? = null,
    val page: Int? = null,
    val results: List<Result>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null
)