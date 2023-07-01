package com.sajjadio.trailers.ui.details.utils

import com.sajjadio.trailers.domain.model.Cast
import com.sajjadio.trailers.domain.model.MovieDetails
import com.sajjadio.trailers.domain.model.CommonResult
import com.sajjadio.trailers.domain.model.Poster

sealed class MovieDetailsItem(val data: Any, val rank: Int) {
    data class MovieItem(val movieDetails: MovieDetails) : MovieDetailsItem(movieDetails, 0)
    data class GalleryItem(val poster: List<Poster>) : MovieDetailsItem(poster, 1)
    data class PersonOfMovieItem(val person: List<Cast>) : MovieDetailsItem(person, 2)
    data class SimilarItemMovie(val commonResult: List<CommonResult>) : MovieDetailsItem(commonResult, 3)
}
