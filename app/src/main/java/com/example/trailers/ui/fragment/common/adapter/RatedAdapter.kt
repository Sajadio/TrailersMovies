package com.example.trailers.ui.fragment.common.adapter

import android.util.Log
import androidx.core.view.isVisible
import com.example.trailers.R
import com.example.trailers.data.model.movie.common.CommonResult
import com.example.trailers.databinding.LayoutItemCardRatedBinding
import com.example.trailers.ui.base.adapter.BaseAdapter
import com.example.trailers.ui.fragment.home.adapter.OnClickListener

class RatedAdapter(
    data: List<CommonResult>,
    private val listener: OnClickListener,
) :
    BaseAdapter<LayoutItemCardRatedBinding, CommonResult>(data) {

    override val layoutId = R.layout.layout_item_card_rated
    override fun bind(
        binding: LayoutItemCardRatedBinding,
        layoutPosition: Int,
        item: CommonResult,
    ) {
        binding.apply {
            seeMore.isVisible = (layoutPosition == 9)
            rated = item
            root.setOnClickListener {
                listener.clickItem(item.id)
            }
            seeMore.setOnClickListener {
                listener.clickItem(navigation = R.string.rated)
            }
        }
    }
}