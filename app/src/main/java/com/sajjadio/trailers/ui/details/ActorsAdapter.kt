package com.sajjadio.trailers.ui.details

import com.sajjadio.trailers.R
import com.sajjadio.trailers.data.model.movie.actors.Cast
import com.sajjadio.trailers.ui.base.BaseAdapter

class ActorsAdapter(
    items: List<Cast>,
    listener: DetailsInteractListener
) : BaseAdapter<Cast>(items,listener) {
    override var layoutId = R.layout.layout_item_actors
}