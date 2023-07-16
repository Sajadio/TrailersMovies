package com.sajjadio.trailers.data.dataSource.mapper

import com.sajjadio.trailers.data.dataSource.local.entites.FavoriteMovieEntity
import com.sajjadio.trailers.domain.model.MovieDetails


internal fun mapMovieDetailsDomainToFavoriteMovieEntity(input: MovieDetails): FavoriteMovieEntity {
    return FavoriteMovieEntity(
        id = input.id,
        original_title = input.original_title,
        runtime = input.runtime,
        poster_path = input.poster_path,
    )
}
