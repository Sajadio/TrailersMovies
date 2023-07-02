package com.sajjadio.trailers.ui.movie_details.utils

sealed class MovieDetailsDestinationType {
    data class PersonItem(val personId: Int) : MovieDetailsDestinationType()
    object Persons : MovieDetailsDestinationType()
    object Similar : MovieDetailsDestinationType()
    class SimilarItem(val movieId: Int) : MovieDetailsDestinationType()
}