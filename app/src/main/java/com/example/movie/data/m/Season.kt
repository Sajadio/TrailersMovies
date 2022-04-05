package com.example.movie.data.m

import com.example.movie.utils.ListAdapterItem2

data class Season(
    val season_number: Int
):ListAdapterItem2 {
    override val ids = season_number
}