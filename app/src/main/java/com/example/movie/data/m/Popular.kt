package com.example.movie.data.m

import androidx.annotation.DrawableRes

data class Popular(
    val ids: Int,
    val title: String,
    val type: Type,
    val rate: Float,
    @field:DrawableRes
    val posterId: Int
)