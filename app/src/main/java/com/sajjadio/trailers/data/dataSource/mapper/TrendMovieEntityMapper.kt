package com.sajjadio.trailers.data.dataSource.mapper

import com.sajjadio.trailers.data.dataSource.local.entites.TrendMovieEntity
import com.sajjadio.trailers.data.dataSource.model.movie.trend.TrendResultDto


internal fun mapToTrendMovieEntity(input: List<TrendResultDto>): List<TrendMovieEntity> {
    return input.map {
        TrendMovieEntity(
            id = it.id,
            original_title = it.original_title,
            poster_path = it.poster_path,
        )
    }
}
