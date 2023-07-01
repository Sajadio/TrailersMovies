package com.sajjadio.trailers.data.mapper

import com.sajjadio.trailers.data.model.movie.movie_details.PosterDto
import com.sajjadio.trailers.domain.model.Poster

internal fun mapImagesOfMovieDtoToImagesOfMovie(input: List<PosterDto?>?): List<Poster>? {
    return input?.map {
        Poster(
            filePath = it?.filePath,
        )
    }
}

