package com.sajjadio.trailers.data.dataSource.model.movie.playnow

data class MoviePlayNow(
    val dates: Dates? = null,
    val page: Int? = null,
    val results: List<PlayNowResult>,
    val total_pages: Int? = null,
    val total_results: Int? = null
)