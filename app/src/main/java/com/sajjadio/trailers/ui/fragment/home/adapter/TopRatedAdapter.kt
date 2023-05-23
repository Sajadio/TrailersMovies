package com.sajjadio.trailers.ui.fragment.home.adapter

import com.sajjadio.trailers.R
import com.sajjadio.trailers.data.model.movie.common.CommonResult
import com.sajjadio.trailers.ui.base.BaseAdapter

class TopRatedAdapter(
    items: List<CommonResult>,
    listener: HomeInteractListener
) : BaseAdapter<CommonResult>(items, listener) {
    override var layoutId = R.layout.layout_item_top_rated
}