package com.example.movie.domain.model.tv.seasons

data class Episode(
    val air_date: String,
    val crew: List<Any>,
    val episode_number: Int,
    val guest_stars: List<Any>,
    val id: Int,
    val name: String,
    val overview: String,
    val production_code: String,
    val season_number: Int,
    val still_path: String,
    val vote_average: Double,
    val vote_count: Int
)