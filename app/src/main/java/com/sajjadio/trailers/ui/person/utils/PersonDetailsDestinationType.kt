package com.sajjadio.trailers.ui.person.utils

sealed class PersonDetailsDestinationType {
    object Movies : PersonDetailsDestinationType()
    class MovieItem(val movieId: Int) : PersonDetailsDestinationType()
}