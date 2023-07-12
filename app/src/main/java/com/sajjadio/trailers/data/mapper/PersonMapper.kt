package com.sajjadio.trailers.data.mapper

import com.sajjadio.trailers.data.model.movie.person.PersonDto
import com.sajjadio.trailers.domain.model.Person

internal fun mapToPerson(input: PersonDto): Person {
    return Person(
        alsoKnownAs = input.alsoKnownAs,
        biography = input.biography,
        birthday = input.birthday,
        id = input.id,
        name = input.name,
        placeOfBirth = input.placeOfBirth,
        popularity = input.popularity,
        profilePath = input.profilePath,
    )
}