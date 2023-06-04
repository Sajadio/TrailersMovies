package com.sajjadio.trailers.domain.mapper

import com.sajjadio.trailers.data.model.movie.movie_details.MovieDetailsDto
import com.sajjadio.trailers.domain.model.MovieDetails
import javax.inject.Inject

class MovieDetailsMapper @Inject constructor() : Mapper<MovieDetailsDto, MovieDetails> {
    override fun mapTo(input: MovieDetailsDto): MovieDetails {
        return MovieDetails(
            backdrop_path = input.backdrop_path,
            genres = input.genres,
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
}