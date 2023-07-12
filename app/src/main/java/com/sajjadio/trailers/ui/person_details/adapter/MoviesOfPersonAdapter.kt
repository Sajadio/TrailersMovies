package com.sajjadio.trailers.ui.person_details.adapter

import com.sajjadio.trailers.R
import com.sajjadio.trailers.domain.model.CommonResult
import com.sajjadio.trailers.ui.base.BaseAdapter

class MoviesOfPersonAdapter(
    items: List<CommonResult>,
    listener: PersonDetailsInteractListener
) : BaseAdapter<CommonResult>(items, listener) {
    override var layoutId = R.layout.layout_small_common_card
}
