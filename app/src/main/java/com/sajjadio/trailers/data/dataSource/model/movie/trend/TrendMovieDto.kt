package com.sajjadio.trailers.data.dataSource.model.movie.trend

data class TrendMovieDto(
    val page: Int?,
    val results: List<TrendResultDto>,
    val total_pages: Int?,
    val total_results: Int?
)