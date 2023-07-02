package com.sajjadio.trailers.data.model.movie.persons

data class Persons(
    val cast: List<CastDto>?,
    val crew: List<Crew>?,
    val id: Int?
)