package com.sajjadio.trailers.data.model.movie.actors

data class Actors(
    val cast: List<CastDto>?,
    val crew: List<Crew>?,
    val id: Int?
)