package com.sajjadio.trailers.ui.person_details.utils

import com.sajjadio.trailers.domain.model.Person
import com.sajjadio.trailers.ui.person_details.adapter.PersonDetailsInteractListener

sealed class PersonDetailsDestinationType {
    object BackButton : PersonDetailsDestinationType()
    class MovieItem(val movieId: Int) : PersonDetailsDestinationType()
    data class BottomSheet(val item: Person, val listener: PersonDetailsInteractListener) : PersonDetailsDestinationType()

}