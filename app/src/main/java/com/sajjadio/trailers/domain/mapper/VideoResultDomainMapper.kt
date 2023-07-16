package com.sajjadio.trailers.domain.mapper


import com.sajjadio.trailers.data.dataSource.model.movie.video.VideoResultDto
import com.sajjadio.trailers.domain.model.Video

internal fun mapDtoToVideoResultMovieDomain(input: List<VideoResultDto?>?): List<Video>? {
    return  input?.map {
        Video(
            key = it?.key
        )
    }
}



