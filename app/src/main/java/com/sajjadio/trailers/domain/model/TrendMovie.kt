package com.sajjadio.trailers.domain.model

data class TrendMovie(
    val page: Int?,
    val results: List<TrendMovieResult>,
    val total_pages: Int?,
    val total_results: Int?
)