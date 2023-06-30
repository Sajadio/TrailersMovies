package com.sajjadio.trailers.domain.model

data class TrendMovieResult(
    val genre_ids: List<Int>?,
    val id: Int,
    val media_type: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val vote_average: Float?,
    val vote_count: Int?
)