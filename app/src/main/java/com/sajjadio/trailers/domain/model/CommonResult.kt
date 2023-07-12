package com.sajjadio.trailers.domain.model

data class CommonResult(
    val id: Int,
    val original_title: String?,
    val overview: String?,
    val poster_path: String?,
    val vote_average: Float?,
    val vote_count: Int?,
    val release_date: String?,
)