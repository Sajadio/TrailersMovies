package com.example.trailers.ui.fragment.common.adapter

import androidx.core.view.isVisible
import com.example.trailers.R
import com.example.trailers.data.model.movie.common.CommonResult
import com.example.trailers.databinding.LayoutItemCardUpcomingBinding
import com.example.trailers.ui.base.adapter.BaseAdapter
import com.example.trailers.ui.fragment.home.adapter.OnClickListener

class UpcomingAdapter(
    data: List<CommonResult>,
    private val listener: OnClickListener,
) : BaseAdapter<LayoutItemCardUpcomingBinding, CommonResult>(data) {


    override val layoutId = R.layout.layout_item_card_upcoming

    override fun bind(
        binding: LayoutItemCardUpcomingBinding,
        layoutPosition: Int,
        item: CommonResult,
    ) {
        binding.apply {
            seeMore.isVisible = (layoutPosition == 9)
            upcoming = item
            root.setOnClickListener {
                listener.clickItem(item.id)
            }
            seeMore.setOnClickListener {
                listener.clickItem(navigation = R.string.upComing)
            }
        }
    }

}