package com.sajjadio.trailers.domain.model

data class Common(
    val page: Int?,
    val results: List<CommonResult>,
    val total_pages: Int?,
    val total_results: Int?,
)