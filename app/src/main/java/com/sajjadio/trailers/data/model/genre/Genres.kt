package com.sajjadio.trailers.data.model.genre

import com.sajjadio.trailers.utils.ParentListAdapter

data class Genres(
    val id: Int,
    val name: String?,
) : ParentListAdapter {
    override val item = id
}