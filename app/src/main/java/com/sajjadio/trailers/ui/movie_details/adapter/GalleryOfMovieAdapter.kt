package com.sajjadio.trailers.ui.movie_details.adapter

import com.sajjadio.trailers.R
import com.sajjadio.trailers.domain.model.Image
import com.sajjadio.trailers.ui.base.BaseAdapter

class GalleryOfMovieAdapter(
    items: List<Image>,
    listener: MovieDetailsInteractListener
) : BaseAdapter<Image>(items, listener) {
    override var layoutId = R.layout.layout_gallery_of_person_movie_card
}

