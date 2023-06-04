package com.sajjadio.trailers.ui.details.adapter

import com.sajjadio.trailers.R
import com.sajjadio.trailers.domain.model.SimilarResult
import com.sajjadio.trailers.ui.base.BaseAdapter
import com.sajjadio.trailers.ui.details.DetailsInteractListener

class SimilarAdapter(
    items: List<SimilarResult>,
    listener: DetailsInteractListener
) : BaseAdapter<SimilarResult>(items, listener) {
    override var layoutId = R.layout.layout_item_similar
}

