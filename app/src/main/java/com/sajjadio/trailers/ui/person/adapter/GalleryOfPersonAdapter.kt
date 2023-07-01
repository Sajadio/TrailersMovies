package com.sajjadio.trailers.ui.person.adapter

import com.sajjadio.trailers.R
import com.sajjadio.trailers.domain.model.Poster
import com.sajjadio.trailers.domain.model.Profile
import com.sajjadio.trailers.ui.base.BaseAdapter

class GalleryOfPersonAdapter (
    items: List<Profile>,
    listener: PersonDetailsInteractListener
) : BaseAdapter<Profile>(items, listener) {
    override var layoutId = R.layout.layout_gallery_of_person_card
}

