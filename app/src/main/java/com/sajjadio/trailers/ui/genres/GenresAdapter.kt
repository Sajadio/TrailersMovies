package com.sajjadio.trailers.ui.genres

import com.sajjadio.trailers.R
import com.sajjadio.trailers.domain.model.GenresOfMovie
import com.sajjadio.trailers.ui.base.BaseAdapter
import com.sajjadio.trailers.ui.base.BaseInteractListener


class GenresAdapter(
    items: List<GenresOfMovie>,
    listener: GenresInteractListener
) : BaseAdapter<GenresOfMovie>(items, listener) {
    override var layoutId = R.layout.layout_genre_card
}

interface GenresInteractListener:BaseInteractListener{
    fun onClickGenreItem(title:String,id:Int)
}

