package com.sajjadio.trailers.ui.person.adapter

import com.sajjadio.trailers.R
import com.sajjadio.trailers.domain.model.Poster
import com.sajjadio.trailers.ui.base.BaseAdapter

class GalleryOfPersonAdapter (
    items: List<Poster>,
    listener: PersonDetailsInteractListener
) : BaseAdapter<Poster>(items, listener) {
    override var layoutId = R.layout.layout_gallery_of_person_card
}

