package com.sajjadio.trailers.ui.details.adapter

import com.sajjadio.trailers.R
import com.sajjadio.trailers.data.model.movie.similar.SimilarResultDto
import com.sajjadio.trailers.ui.base.BaseAdapter
import com.sajjadio.trailers.ui.details.DetailsInteractListener

class SimilarAdapter(
    items: List<SimilarResultDto>,
    listener: DetailsInteractListener
) : BaseAdapter<SimilarResultDto>(items, listener) {
    override var layoutId = R.layout.layout_item_similar
}

