package com.sajjadio.trailers.data.dataSource.mapper

import com.sajjadio.trailers.data.dataSource.model.movie.persons.CastDto
import com.sajjadio.trailers.domain.model.Cast

internal fun mapToPersonOfMovie(input: List<CastDto>): List<Cast> {
    return input.map {
        Cast(
            id = it.id,
            original_name = it.original_name,
            profile_path = it.profile_path,
        )
    }
}