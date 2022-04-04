package com.example.movie.data.model

import androidx.annotation.DrawableRes
import com.example.movie.utils.ViewType

data class Category(
    val ids: Int,
    val title: String,
    val type: Type,
    val rate: Float,
    @field:DrawableRes
    val posterId: Int
)