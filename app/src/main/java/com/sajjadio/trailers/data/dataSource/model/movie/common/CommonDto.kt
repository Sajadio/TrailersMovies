package com.sajjadio.trailers.data.dataSource.model.movie.common

data class CommonDto(
    val page: Int?,
    val results: List<CommonResultDto>,
    val total_pages: Int?,
    val total_results: Int?,
)