package com.sajjadio.trailers.data.mapper

import com.sajjadio.trailers.data.model.movie.common.ImageCommonDto
import com.sajjadio.trailers.domain.model.Image

internal fun mapImagesDtoToImages(input: List<ImageCommonDto?>?): List<Image>? {
    return input?.map {
        Image(
            filePath = it?.filePath,
            height = it?.height,
            width = it?.width
        )
    }
}

