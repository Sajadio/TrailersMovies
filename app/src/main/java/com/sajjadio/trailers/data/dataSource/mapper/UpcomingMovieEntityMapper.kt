package com.sajjadio.trailers.data.dataSource.mapper

import com.sajjadio.trailers.data.dataSource.local.entites.UpcomingMovieEntity
import com.sajjadio.trailers.data.dataSource.model.movie.common.CommonResultDto


internal fun mapToUpcomingMovieEntity(input: List<CommonResultDto>): List<UpcomingMovieEntity> {
    return input.map {
        UpcomingMovieEntity(
            id = it.id,
            original_title = it.original_title,
            poster_path = it.poster_path,
            vote_average = it.vote_average,
            release_date = it.release_date,
        )
    }
}

