package com.sajjadio.trailers.data.dataSource.model.movie.genremovie

data class Movie(
    val page: Int?,
    val results: List<MovieResult>?,
    val total_pages: Int?,
    val total_results: Int?
)