package com.sajjadio.trailers.ui.common


import com.sajjadio.trailers.R
import com.sajjadio.trailers.domain.model.CommonResult
import com.sajjadio.trailers.ui.base.BaseInteractListener
import com.sajjadio.trailers.ui.base.BasePagingAdapter

class CommonPagingAdapter(
    listener: BaseInteractListener
) : BasePagingAdapter<CommonResult>(listener) {
    override var layoutId = R.layout.layout_normal_common_card
}