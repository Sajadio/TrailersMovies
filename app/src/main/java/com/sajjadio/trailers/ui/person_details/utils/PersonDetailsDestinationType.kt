package com.sajjadio.trailers.ui.person_details.utils

sealed class PersonDetailsDestinationType {
    object BackButton : PersonDetailsDestinationType()
    object Movies : PersonDetailsDestinationType()
    class MovieItem(val movieId: Int) : PersonDetailsDestinationType()
}