package com.example.trailers.data.model.movie.rate

import com.example.trailers.utils.ParentListAdapter

data class TopRatedMovie(
    val page: Int?,
    val results: List<Result>?,
    val total_pages: Int?,
    val total_results: Int?
): ParentListAdapter