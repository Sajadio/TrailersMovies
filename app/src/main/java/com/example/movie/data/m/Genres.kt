package com.example.movie.data.m

import androidx.annotation.DrawableRes

data class Genres(
    val ids: Int,
    val title: String,
    val type: Type,
    val rate: Float,
    @field:DrawableRes
    val posterId: Int
)