package com.sajjadio.trailers.data.model.movie.search

data class SearchMovie(
    val page: Int?,
    val searchResults: List<SearchResult>?,
    val total_pages: Int?,
    val total_results: Int?
)