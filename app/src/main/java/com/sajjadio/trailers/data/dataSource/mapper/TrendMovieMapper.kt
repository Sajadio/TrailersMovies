package com.sajjadio.trailers.data.dataSource.mapper

import com.sajjadio.trailers.data.dataSource.model.movie.trend.TrendMovieDto
import com.sajjadio.trailers.data.dataSource.model.movie.trend.TrendResultDto
import com.sajjadio.trailers.domain.model.TrendMovie
import com.sajjadio.trailers.domain.model.TrendMovieResult

internal fun mapToTrendMove(input: TrendMovieDto): TrendMovie {
    return TrendMovie(
        page = input.page,
        results = mapToTrendMovieResult(input.results),
        total_pages = input.total_pages,
        total_results = input.total_results,
    )
}

internal fun mapToTrendMovieResult(input: List<TrendResultDto>): List<TrendMovieResult> {
    return input.map {
        TrendMovieResult(
            id = it.id,
            original_title = it.original_title,
            overview = it.overview,
            poster_path = it.poster_path,
            vote_average = it.vote_average?.toFloat(),
            vote_count = it.vote_count,
            release_date = it.release_date,
            media_type = it.media_type,
            genre_ids = it.genre_ids,
            popularity = it.popularity
        )
    }
}

