package com.sajjadio.trailers.data.dataSource.mapper


import com.sajjadio.trailers.data.dataSource.model.movie.common.CommonResultDto
import com.sajjadio.trailers.domain.model.SearchMovieResult


internal fun mapSearchMovieEntity(input: List<CommonResultDto>): List<SearchMovieResult> {
    return input.map {
        SearchMovieResult(
            id = it.id,
            original_title = it.original_title,
            poster_path = it.poster_path,
            vote_average = it.vote_average,
            release_date = it.release_date,
            vote_count = it.vote_count
        )
    }
}

