package com.example.trailers.ui.fragment.rated.adapter

import androidx.core.view.isVisible
import com.example.trailers.R
import com.example.trailers.data.model.movie.common.CommonResult
import com.example.trailers.databinding.LayoutItemCardRatedBinding
import com.example.trailers.ui.base.adapter.BaseAdapter
import com.example.trailers.ui.fragment.home.adapter.OnClickListener

class RatedAdapter(
    data: List<CommonResult>
) :
    BaseAdapter<LayoutItemCardRatedBinding, CommonResult>(data) {

    private var onItemClickListener: ((Int) -> Unit)? = null
    fun onItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

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
               onItemClickListener?.let { it(item.id) }
            }
            seeMore.setOnClickListener {
                onItemClickListener?.let { it(R.string.rated) }

            }
        }
    }
}