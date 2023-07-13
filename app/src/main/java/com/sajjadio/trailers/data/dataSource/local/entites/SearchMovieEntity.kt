package com.sajjadio.trailers.data.dataSource.local.entites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sajjadio.trailers.utils.Constant.SEARCH_MOVIE_TABLE_NAME


@Entity(tableName = SEARCH_MOVIE_TABLE_NAME)
data class SearchMovieEntity(
    @PrimaryKey
    val id: Int,
    val original_title: String?,
    val poster_path: String?,
    val release_date: String?,
    val vote_average: Double?,
    val vote_count: Int?,
)