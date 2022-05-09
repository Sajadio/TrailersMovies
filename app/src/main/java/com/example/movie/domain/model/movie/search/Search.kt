package com.example.movie.domain.model.movie.search

import com.example.movie.utils.ParentListAdapter

data class Search(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int,
) : ParentListAdapter {
    override val ids = page
}