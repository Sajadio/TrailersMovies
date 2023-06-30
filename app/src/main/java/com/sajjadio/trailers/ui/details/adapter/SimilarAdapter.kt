package com.sajjadio.trailers.ui.details.adapter

import com.sajjadio.trailers.R
import com.sajjadio.trailers.domain.model.CommonResult
import com.sajjadio.trailers.ui.base.BaseAdapter
import com.sajjadio.trailers.ui.details.DetailsInteractListener

class SimilarAdapter(
    items: List<CommonResult>,
    listener: DetailsInteractListener
) : BaseAdapter<CommonResult>(items, listener) {
    override var layoutId = R.layout.layout_small_common_card
}

