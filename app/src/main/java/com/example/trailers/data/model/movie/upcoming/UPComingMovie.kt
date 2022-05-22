package com.example.trailers.data.model.movie.upcoming

import com.example.trailers.utils.ParentListAdapter

data class UPComingMovie(
    val dates: Dates?,
    val page: Int?,
    val results: List<Result>?,
    val total_pages: Int?,
    val total_results: Int?
): ParentListAdapter