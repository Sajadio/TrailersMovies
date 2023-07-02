package com.sajjadio.trailers.ui.details.utils

import android.widget.ImageView

sealed class MovieDetailsDestinationType {
    data class PersonItem(val personId: Int) : MovieDetailsDestinationType()
    object Persons : MovieDetailsDestinationType()
    object Similar : MovieDetailsDestinationType()
    class SimilarItem(val movieId: Int) : MovieDetailsDestinationType()
}