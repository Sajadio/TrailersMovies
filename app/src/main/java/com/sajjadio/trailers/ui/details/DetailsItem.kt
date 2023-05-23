package com.sajjadio.trailers.ui.details

import com.sajjadio.trailers.data.model.movie.actors.Actors
import com.sajjadio.trailers.data.model.movie.id.IDMovie
import com.sajjadio.trailers.data.model.movie.similar.Similar

sealed class DetailsItem(val data: Any, val rank: Int) {
    data class MovieItem(val movie: IDMovie) : DetailsItem(movie, 0)
    data class ActorItem(val actors: Actors) : DetailsItem(actors, 1)
    data class SimilarItem(val similar: Similar) : DetailsItem(similar, 2)
}
