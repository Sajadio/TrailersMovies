package com.sajjadio.trailers.ui.movie_details.utils

import com.sajjadio.trailers.domain.model.MovieDetails
import com.sajjadio.trailers.ui.movie_details.adapter.MovieDetailsInteractListener

sealed class MovieDetailsDestinationType {
    data class PersonItem(val personId: Int) : MovieDetailsDestinationType()
    object Persons : MovieDetailsDestinationType()
    object Similar : MovieDetailsDestinationType()
    class SimilarItem(val movieId: Int) : MovieDetailsDestinationType()
    object BackButton : MovieDetailsDestinationType()
    data class FavoriteItem(val item: String) : MovieDetailsDestinationType()
    data class WatchNowMovie(val movieId: Int) : MovieDetailsDestinationType()
    data class BottomSheet(val item: MovieDetails, val listener:MovieDetailsInteractListener) : MovieDetailsDestinationType()

}