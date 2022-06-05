package com.example.trailers.ui.fragment.common.adapter

import androidx.core.view.isVisible
import com.example.trailers.R
import com.example.trailers.data.model.movie.common.CommonResult
import com.example.trailers.databinding.LayoutItemCardPopualrBinding
import com.example.trailers.ui.base.adapter.BaseAdapter
import com.example.trailers.ui.fragment.home.adapter.OnClickListener

class PopularAdapter(
     data: List<CommonResult>,
    private val listener: OnClickListener,
) : BaseAdapter<LayoutItemCardPopualrBinding, CommonResult>(data) {

    override val layoutId = R.layout.layout_item_card_popualr

    override fun bind(
        binding: LayoutItemCardPopualrBinding,
        layoutPosition: Int,
        item: CommonResult,
    ) {
        binding.apply {
            seeMore.isVisible = (layoutPosition == 9)
            popular = item
            root.setOnClickListener {
                listener.clickItem(item.id)
            }
            seeMore.setOnClickListener {
                listener.clickItem(navigation = R.string.popular)
            }
        }
    }
}