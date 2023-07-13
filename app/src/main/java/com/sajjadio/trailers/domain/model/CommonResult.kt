package com.sajjadio.trailers.domain.model

data class CommonResult(
    val id: Int,
    val original_title: String?,
    val poster_path: String?,
    val release_date: String?,
    val vote_average: Double?,
)