package com.sajjadio.trailers.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sajjadio.trailers.utils.Constant.MOVIE_TABLE_NAME

@Entity(tableName = MOVIE_TABLE_NAME)
data class MovieDetails(
    @PrimaryKey
    val id: Int,
    val backdrop_path: String?,
    val genresOfMovies: List<Genre>,
    val original_title: String,
    val overview: String,
    val runtime: Int,
    val poster_path: String?,
    val release_date: String,
    val vote_average: Float,
    val vote_count: Int
)