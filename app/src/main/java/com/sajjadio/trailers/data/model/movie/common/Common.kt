package com.sajjadio.trailers.data.model.movie.common

data class Common(
    val page: Int?,
    val results: List<CommonResult>,
    val total_pages: Int?,
    val total_results: Int?,
)