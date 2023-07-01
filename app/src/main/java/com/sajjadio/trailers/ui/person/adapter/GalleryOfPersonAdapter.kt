package com.sajjadio.trailers.ui.person.adapter

import com.sajjadio.trailers.R
import com.sajjadio.trailers.domain.model.Image
import com.sajjadio.trailers.ui.base.BaseAdapter

class GalleryOfPersonAdapter (
    items: List<Image>,
    listener: PersonDetailsInteractListener
) : BaseAdapter<Image>(items, listener) {
    override var layoutId = R.layout.layout_gallery_of_person_card
}

