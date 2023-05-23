package com.sajjadio.trailers.ui.details

import com.sajjadio.trailers.R
import com.sajjadio.trailers.data.model.movie.actorsmovie.Cast
import com.sajjadio.trailers.ui.actors.ActorInteractListener
import com.sajjadio.trailers.ui.base.BaseAdapter

class ActorsAdapter(
    items: List<Cast>,
    listener: ActorInteractListener
) : BaseAdapter<Cast>(items,listener) {
    override var layoutId = R.layout.layout_item_actors
}