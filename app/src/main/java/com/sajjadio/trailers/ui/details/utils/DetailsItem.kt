package com.sajjadio.trailers.ui.details.utils

import com.sajjadio.trailers.data.model.movie.actors.ActorsDto
import com.sajjadio.trailers.data.model.movie.id.IDMovie
import com.sajjadio.trailers.data.model.movie.similar.Similar

sealed class DetailsItem(val data: Any, val rank: Int) {
    data class MovieItem(val movie: IDMovie) : DetailsItem(movie, 0)
    data class ActorItem(val actorsDto: ActorsDto) : DetailsItem(actorsDto, 1)
    data class SimilarItem(val similar: Similar) : DetailsItem(similar, 2)
}
