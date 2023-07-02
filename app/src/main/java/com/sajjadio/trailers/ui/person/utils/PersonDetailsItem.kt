package com.sajjadio.trailers.ui.person.utils

import com.sajjadio.trailers.domain.model.CommonResult
import com.sajjadio.trailers.domain.model.Person
import com.sajjadio.trailers.domain.model.Image

sealed class PersonDetailsItem(val data: Any, val rank: Int) {
    data class PersonItem(val person: Person) : PersonDetailsItem(person, 0)
    data class GalleryOfPersonItem(val image: List<Image>) : PersonDetailsItem(image, 1)
    data class MoviesOfPersonItem(val commonResult: List<CommonResult>) : PersonDetailsItem(commonResult, 2)
}
