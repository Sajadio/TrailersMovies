package com.example.movie.data.m

import androidx.annotation.DrawableRes
import com.example.movie.utils.ParentListAdapter

data class Genres(
    val id: Int,
    val title: String,
    val type: Type,
    val rate: Float,
    @field:DrawableRes
    val posterId: Int
):ParentListAdapter {
    override val ids = id
}