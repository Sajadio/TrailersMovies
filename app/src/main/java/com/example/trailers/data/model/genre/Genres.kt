package com.example.trailers.data.model.genre

import com.example.trailers.utils.ParentListAdapter

data class Genres(
    val id: Int,
    val name: String?,
) : ParentListAdapter {
    override val item = id
}