package com.sajjadio.trailers.data.mapper

import com.sajjadio.trailers.data.model.movie.movie_details.PosterDto
import com.sajjadio.trailers.data.model.movie.person.ProfileDto
import com.sajjadio.trailers.domain.model.Poster
import com.sajjadio.trailers.domain.model.Profile

internal fun mapImagesOfPersonDtoToImagesOfPerson(input: List<ProfileDto?>?): List<Profile>? {
    return input?.map {
        Profile(
            filePath = it?.filePath,
            height = it?.height,
            width = it?.width
        )
    }
}

