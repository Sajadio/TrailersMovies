package com.sajjadio.trailers.ui.details.adapter

import com.sajjadio.trailers.R
import com.sajjadio.trailers.data.model.movie.actors.Cast
import com.sajjadio.trailers.ui.base.BaseAdapter
import com.sajjadio.trailers.ui.details.DetailsInteractListener

class ActorsAdapter(
    items: List<Cast>,
    listener: DetailsInteractListener
) : BaseAdapter<Cast>(items,listener) {
    override var layoutId = R.layout.layout_item_actors
}