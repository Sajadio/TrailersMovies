package com.sajjadio.trailers.ui.details.adapter

import com.sajjadio.trailers.R
import com.sajjadio.trailers.domain.model.Cast
import com.sajjadio.trailers.ui.base.BaseAdapter

class PersonOfMovieAdapter(
    items: List<Cast>,
    listener: MovieDetailsInteractListener
) : BaseAdapter<Cast>(items,listener) {
    override var layoutId = R.layout.layout_item_person_of_movie
}