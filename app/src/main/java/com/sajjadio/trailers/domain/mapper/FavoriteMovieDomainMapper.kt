package com.sajjadio.trailers.domain.mapper

import com.sajjadio.trailers.data.dataSource.local.entites.FavoriteMovieEntity
import com.sajjadio.trailers.domain.model.FavoriteMovie


internal fun mapEntityToFavoriteMovieDomain(
    input: FavoriteMovieEntity,
): FavoriteMovie {
    return FavoriteMovie(
        id = input.id,
        original_title = input.original_title,
        runtime = input.runtime,
        poster_path = input.poster_path,
    )
}