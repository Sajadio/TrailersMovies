package com.sajjadio.trailers.data.dataSource.model.movie.persons

data class PersonsDto   (
    val cast: List<CastDto>?,
    val crew: List<Crew>?,
    val id: Int?
)