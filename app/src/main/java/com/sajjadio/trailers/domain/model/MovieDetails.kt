package com.sajjadio.trailers.domain.model

import com.sajjadio.trailers.data.model.movie.movie_details.Genre

data class MovieDetails(
    val backdrop_path: String?,
    val genres: List<Genre>,
    val id: Int,
    val original_title: String,
    val overview: String,
    val runtime: Int,
    val poster_path: String?,
    val release_date: String,
    val vote_average: Double,
    val vote_count: Int
)