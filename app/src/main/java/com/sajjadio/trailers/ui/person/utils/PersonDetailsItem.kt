package com.sajjadio.trailers.ui.person.utils

import com.sajjadio.trailers.data.model.movie.person.PersonDto
import com.sajjadio.trailers.domain.model.CommonResult
import com.sajjadio.trailers.domain.model.Person
import com.sajjadio.trailers.domain.model.Poster

sealed class PersonDetailsItem(val data: Any, val rank: Int) {
    data class PersonItem(val person: Person) : PersonDetailsItem(person, 0)
    data class GalleryOFPersonItem(val poster: List<Poster>) : PersonDetailsItem(poster, 1)
    data class MoviesOfPersonItem(val commonResult: List<CommonResult>) : PersonDetailsItem(commonResult, 2)
}
