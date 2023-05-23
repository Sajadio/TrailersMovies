package com.sajjadio.trailers.ui.details

import com.sajjadio.trailers.R
import com.sajjadio.trailers.data.model.movie.similar.Result
import com.sajjadio.trailers.ui.base.BaseAdapter

class SimilarAdapter(
    items: List<Result>,
    listener: SimilarInteractionist
) : BaseAdapter<Result>(items, listener) {
    override var layoutId = R.layout.layout_item_similar
}

