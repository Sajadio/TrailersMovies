package com.example.trailers.data.model.movie.popular

import com.example.trailers.utils.ParentListAdapter

data class PopularMovie(
    val page: Int?,
    val results: List<Result>?,
    val total_pages: Int?,
    val total_results: Int?
):ParentListAdapter