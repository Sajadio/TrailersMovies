package com.sajjadio.trailers.domain.mapper

import com.sajjadio.trailers.data.dataSource.local.entites.TrendMovieEntity
import com.sajjadio.trailers.domain.model.TrendMovie

internal fun mapToTrendMovieDomain(input: TrendMovieEntity): TrendMovie {
    return TrendMovie(
            id = input.id,
            original_title = input.original_title,
            poster_path = input.poster_path,
        )

}

