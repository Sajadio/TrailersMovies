package com.sajjadio.trailers.data.dataSource.mapper

import com.sajjadio.trailers.data.dataSource.model.movie.movie_details.GenreDto
import com.sajjadio.trailers.data.dataSource.model.movie.movie_details.MovieDetailsDto
import com.sajjadio.trailers.domain.model.Genre
import com.sajjadio.trailers.domain.model.MovieDetails

internal fun mapToMovieDetails(input: MovieDetailsDto): MovieDetails {
    return MovieDetails(
        backdrop_path = input.backdrop_path,
        genresOfMovies = mapToGenreDomain(input.genres),
        id = input.id,
        original_title = input.original_title,
        overview = input.overview,
        runtime = input.runtime,
        poster_path = input.poster_path,
        release_date = input.release_date,
        vote_average = input.vote_average.toFloat(),
        vote_count = input.vote_count,
    )
}

private fun mapToGenreDomain(input: List<GenreDto>): List<Genre> {
    return input.map {
        Genre(
            id = it.id,
            name = it.name,
        )
    }
}