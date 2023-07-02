package com.sajjadio.trailers.data.mapper

import com.sajjadio.trailers.data.model.movie.persons.CastDto
import com.sajjadio.trailers.domain.model.Cast

internal fun mapPersonOfMovieDtoToPersonOfMovie(input: List<CastDto>): List<Cast> {
    return input.map {
        Cast(
            id = it.id,
            original_name = it.original_name,
            profile_path = it.profile_path,
        )
    }
}