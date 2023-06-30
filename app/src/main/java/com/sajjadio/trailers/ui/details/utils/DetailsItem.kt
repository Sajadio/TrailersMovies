package com.sajjadio.trailers.ui.details.utils

import com.sajjadio.trailers.domain.model.Cast
import com.sajjadio.trailers.domain.model.MovieDetails
import com.sajjadio.trailers.domain.model.CommonResult
import com.sajjadio.trailers.domain.model.Poster

sealed class DetailsItem(val data: Any, val rank: Int) {
    data class MovieItem(val movieDetails: MovieDetails) : DetailsItem(movieDetails, 0)
    data class GalleryItem(val poster: List<Poster>) : DetailsItem(poster, 1)
    data class ActorItem(val actors: List<Cast>) : DetailsItem(actors, 2)
    data class SimilarItem(val commonResult: List<CommonResult>) : DetailsItem(commonResult, 3)
}
