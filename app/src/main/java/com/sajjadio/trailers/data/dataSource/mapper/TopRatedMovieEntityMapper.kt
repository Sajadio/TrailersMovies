package com.sajjadio.trailers.data.dataSource.mapper

import com.sajjadio.trailers.data.dataSource.local.entites.TopRatedMovieEntity
import com.sajjadio.trailers.data.dataSource.model.movie.common.CommonResultDto


internal fun mapToTopRatedMovieEntity(input: List<CommonResultDto>): List<TopRatedMovieEntity> {
    return input.map {
        TopRatedMovieEntity(
            id = it.id,
            original_title = it.original_title,
            poster_path = it.poster_path,
            vote_average = it.vote_average,
            release_date = it.release_date,
        )
    }
}

