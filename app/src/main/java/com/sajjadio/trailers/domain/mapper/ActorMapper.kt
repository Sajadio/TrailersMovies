package com.sajjadio.trailers.domain.mapper

import com.sajjadio.trailers.data.model.movie.actors.CastDto
import com.sajjadio.trailers.domain.model.Cast
import javax.inject.Inject

class ActorMapper @Inject constructor() : Mapper<List<CastDto>, List<Cast>> {
    override fun mapTo(input: List<CastDto>): List<Cast> {
        return input.map {
            Cast(
                id = it.id,
                original_name = it.original_name,
                profile_path = it.profile_path,
            )
        }
    }
}