package com.sajjadio.trailers.data.mapper

import com.sajjadio.trailers.data.model.movie.images.PosterDto
import com.sajjadio.trailers.domain.model.Poster

internal fun mapImagesDtoToImages(input: List<PosterDto?>?): List<Poster>? {
    return input?.map {
        Poster(
            aspectRatio = it?.aspectRatio,
            filePath = it?.filePath,
            height = it?.height,
            width = it?.width,
        )
    }
}

