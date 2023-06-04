package com.sajjadio.trailers.domain.model

data class SimilarResult(
    val adult: Boolean?,
    val backdrop_path: String?,
    val id: Int,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
)