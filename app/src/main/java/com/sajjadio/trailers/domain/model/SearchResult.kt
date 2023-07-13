package com.sajjadio.trailers.domain.model

data class SearchResult(
    val id: Int,
    val original_title: String?,
    val poster_path: String?,
    val release_date: String?,
    val vote_average: Double?,
    val vote_count: Int?,
)