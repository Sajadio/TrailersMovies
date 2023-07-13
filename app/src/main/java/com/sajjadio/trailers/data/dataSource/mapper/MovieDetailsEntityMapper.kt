package com.sajjadio.trailers.data.dataSource.mapper

import com.sajjadio.trailers.data.dataSource.local.entites.MovieDetailsEntity
import com.sajjadio.trailers.data.dataSource.local.entites.GenreOfMovie
import com.sajjadio.trailers.domain.model.Genre
import com.sajjadio.trailers.domain.model.MovieDetails


internal fun mapDomainToMovieDetailsEntity(input: MovieDetails): MovieDetailsEntity {
    return MovieDetailsEntity(
        backdrop_path = input.backdrop_path,
        genresOfMovies = mapDomainToGenre(input.genresOfMovies),
        id = input.id,
        original_title = input.original_title,
        overview = input.overview,
        runtime = input.runtime,
        poster_path = input.poster_path,
        release_date = input.release_date,
        vote_average = input.vote_average,
        vote_count = input.vote_count,
    )
}


private fun mapDomainToGenre(input: List<Genre>): List<GenreOfMovie> {
    return input.map {
        GenreOfMovie(
            id = it.id,
            name = it.name,
        )
    }
}