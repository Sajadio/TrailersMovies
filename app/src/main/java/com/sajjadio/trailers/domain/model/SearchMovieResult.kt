package com.sajjadio.trailers.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sajjadio.trailers.utils.Constant

@Entity(tableName = Constant.SEARCH_MOVIE_TABLE_NAME)
data class SearchMovieResult(
    @PrimaryKey
    val id: Int,
    val original_title: String?,
    val poster_path: String?,
    val release_date: String?,
    val vote_average: Double?,
    val vote_count: Int?,
)