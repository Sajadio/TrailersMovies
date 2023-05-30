package com.sajjadio.trailers.data.model.movie.similar

data class Similar(
    val page: Int?,
    val similarResults: List<SimilarResult>?,
    val total_pages: Int?,
    val total_results: Int?
)