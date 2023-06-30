package com.sajjadio.trailers.ui.details.adapter

import com.sajjadio.trailers.R
import com.sajjadio.trailers.domain.model.CommonResult
import com.sajjadio.trailers.domain.model.Poster
import com.sajjadio.trailers.ui.base.BaseAdapter
import com.sajjadio.trailers.ui.details.DetailsInteractListener

class GalleryAdapter(
    items: List<Poster>,
    listener: DetailsInteractListener
) : BaseAdapter<Poster>(items, listener) {
    override var layoutId = R.layout.layout_gallery_card
}

