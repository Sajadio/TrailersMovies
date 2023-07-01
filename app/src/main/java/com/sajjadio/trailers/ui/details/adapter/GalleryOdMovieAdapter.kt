package com.sajjadio.trailers.ui.details.adapter

import com.sajjadio.trailers.R
import com.sajjadio.trailers.domain.model.Poster
import com.sajjadio.trailers.ui.base.BaseAdapter

class GalleryOdMovieAdapter(
    items: List<Poster>,
    listener: MovieDetailsInteractListener
) : BaseAdapter<Poster>(items, listener) {
    override var layoutId = R.layout.layout_gallery_of_person_movie_card
}

