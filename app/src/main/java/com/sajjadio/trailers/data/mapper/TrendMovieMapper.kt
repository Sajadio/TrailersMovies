package com.sajjadio.trailers.data.mapper

import com.sajjadio.trailers.data.model.movie.trend.TrendMovieDto
import com.sajjadio.trailers.data.model.movie.trend.TrendResultDto
import com.sajjadio.trailers.domain.model.TrendMovie
import com.sajjadio.trailers.domain.model.TrendMovieResult

internal fun mapTrendMovieDtoTo(input: TrendMovieDto): TrendMovie {
    return TrendMovie(
        page = input.page,
        results = mapTrendMovieResultDtoTo(input.results),
        total_pages = input.total_pages,
        total_results = input.total_results,
    )
}

internal fun mapTrendMovieResultDtoTo(input: List<TrendResultDto>): List<TrendMovieResult> {
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

