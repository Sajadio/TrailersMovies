package com.sajjadio.trailers.data.dataSource.mapper

import com.sajjadio.trailers.data.dataSource.model.movie.common.ImageCommonDto
import com.sajjadio.trailers.domain.model.Image

internal fun mapToImagesDomain(input: List<ImageCommonDto?>?): List<Image>? {
    return input?.map {
        Image(
            filePath = it?.filePath,
            height = it?.height,
            width = it?.width
        )
    }
}

