package com.sajjadio.trailers.ui.similar

import com.sajjadio.trailers.R
import com.sajjadio.trailers.domain.model.CommonResult
import com.sajjadio.trailers.ui.base.BaseInteractListener
import com.sajjadio.trailers.ui.base.BasePagingAdapter

class SimilarPagingAdapter(
    listener: BaseInteractListener
) : BasePagingAdapter<CommonResult>(listener) {
    override var layoutId = R.layout.layout_normal_common_card
}
