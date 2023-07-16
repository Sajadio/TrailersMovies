package com.sajjadio.trailers.domain.model

data class FavoriteMovie(
    val id: Int,
    val original_title: String,
    val runtime: Int,
    val poster_path: String?,
)
