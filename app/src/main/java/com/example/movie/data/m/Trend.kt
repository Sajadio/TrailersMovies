package com.example.movie.data.m

import androidx.annotation.DrawableRes

data class Trend(
    val ids: Int,
    val title: String,
    val type: Type,
    val rate: Float,
    @field:DrawableRes
    val posterId: Int
)