package com.example.movie.data.m

import com.example.movie.utils.ParentListAdapter

data class Season(
    val season_number: Int
):ParentListAdapter {
    override val ids = season_number
}