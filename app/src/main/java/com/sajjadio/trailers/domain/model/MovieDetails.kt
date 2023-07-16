package com.sajjadio.trailers.domain.model


data class MovieDetails(
    val id: Int,
    val backdrop_path: String?,
    val genresOfMovies: List<Genre>,
    val original_title: String,
    val overview: String,
    val runtime: Int,
    val poster_path: String?,
    val release_date: String,
    val vote_average: Float,
    val vote_count: Int,
    val isSavedItem: Boolean?
)