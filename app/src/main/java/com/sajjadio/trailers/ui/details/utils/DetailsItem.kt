package com.sajjadio.trailers.ui.details.utils

import com.sajjadio.trailers.domain.model.Cast
import com.sajjadio.trailers.domain.model.MovieDetails
import com.sajjadio.trailers.domain.model.CommonResult

sealed class DetailsItem(val data: Any, val rank: Int) {
    data class MovieItem(val movieDetails: MovieDetails) : DetailsItem(movieDetails, 0)
    data class ActorItem(val actors: List<Cast>) : DetailsItem(actors, 1)
    data class SimilarItem(val commonResult: List<CommonResult>) : DetailsItem(commonResult, 2)
}
