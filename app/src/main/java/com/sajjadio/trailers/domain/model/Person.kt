package com.sajjadio.trailers.domain.model


data class Person(
    val alsoKnownAs: List<String?>?,
    val biography: String?,
    val birthday: String?,
    val id: Int?,
    val name: String?,
    val placeOfBirth: String?,
    val popularity: Double?,
    val profilePath: String?
)
