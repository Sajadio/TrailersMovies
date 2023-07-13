package com.sajjadio.trailers.data.dataSource.local.entites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sajjadio.trailers.utils.Constant.TREND_MOVIE_TABLE_NAME

@Entity(tableName = TREND_MOVIE_TABLE_NAME)
data class TrendMovieEntity(
    @PrimaryKey
    val id: Int,
    val original_title: String?,
    val poster_path: String?,
)
