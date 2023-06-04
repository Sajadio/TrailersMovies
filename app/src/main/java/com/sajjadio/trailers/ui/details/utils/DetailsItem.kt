package com.sajjadio.trailers.ui.details.utils

import com.sajjadio.trailers.data.model.movie.id.IDMovie
import com.sajjadio.trailers.data.model.movie.similar.Similar
import com.sajjadio.trailers.domain.model.Cast
import com.sajjadio.trailers.domain.model.SimilarResult

sealed class DetailsItem(val data: Any, val rank: Int) {
    data class MovieItem(val movie: IDMovie) : DetailsItem(movie, 0)
    data class ActorItem(val actors: List<Cast>) : DetailsItem(actors, 1)
    data class SimilarItem(val similarResult: List<SimilarResult>) : DetailsItem(similarResult, 2)
}
