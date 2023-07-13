package com.sajjadio.trailers.data.dataSource.local.entites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sajjadio.trailers.utils.Constant.MOVIE_DETAILS_TABLE_NAME

@Entity(tableName = MOVIE_DETAILS_TABLE_NAME)
data class MovieDetailsEntity(
    @PrimaryKey
    val id: Int,
    val backdrop_path: String?,
    val genresOfMovies: List<GenreOfMovie>,
    val original_title: String,
    val overview: String,
    val runtime: Int,
    val poster_path: String?,
    val release_date: String,
    val vote_average: Float,
    val vote_count: Int
)

data class GenreOfMovie(
    val id:Int,
    val name:String
)