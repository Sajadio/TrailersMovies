package com.sajjadio.trailers.data.model.movie.actors

data class ActorsDto(
    val castDto: List<CastDto>?,
    val crew: List<Crew>?,
    val id: Int?
)