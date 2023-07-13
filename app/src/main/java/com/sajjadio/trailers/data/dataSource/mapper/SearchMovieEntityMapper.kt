package com.sajjadio.trailers.data.dataSource.mapper

import com.sajjadio.trailers.data.dataSource.local.entites.SearchMovieEntity
import com.sajjadio.trailers.data.dataSource.model.movie.common.CommonResultDto


internal fun mapSearchMovieEntity(input: List<CommonResultDto>): List<SearchMovieEntity> {
    return input.map {
        SearchMovieEntity(
            id = it.id,
            original_title = it.original_title,
            poster_path = it.poster_path,
            vote_average = it.vote_average,
            release_date = it.release_date,
            vote_count = it.vote_count
        )
    }
}

