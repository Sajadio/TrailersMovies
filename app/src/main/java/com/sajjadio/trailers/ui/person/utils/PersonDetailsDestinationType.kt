package com.sajjadio.trailers.ui.person.utils

sealed class PersonDetailsDestinationType {
    object PersonsDetails : PersonDetailsDestinationType()
    object GalleryItem : PersonDetailsDestinationType()
    object Galleries : PersonDetailsDestinationType()
    object Movies : PersonDetailsDestinationType()
    class MovieItem(val movieId: Int) : PersonDetailsDestinationType()
}