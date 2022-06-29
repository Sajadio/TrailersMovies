package com.example.trailers.ui.fragment.popular.adapter

import androidx.core.view.isVisible
import com.example.trailers.R
import com.example.trailers.data.model.movie.common.CommonResult
import com.example.trailers.databinding.LayoutItemCardPopualrBinding
import com.example.trailers.ui.base.adapter.BaseAdapter
import com.example.trailers.ui.fragment.home.adapter.OnClickListener

class PopularAdapter(
     data: List<CommonResult>
) : BaseAdapter<LayoutItemCardPopualrBinding, CommonResult>(data) {

    override val layoutId = R.layout.layout_item_card_popualr

    private var onItemClickListener: ((Int) -> Unit)? = null
    fun onItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

    override fun bind(
        binding: LayoutItemCardPopualrBinding,
        layoutPosition: Int,
        item: CommonResult,
    ) {
        binding.apply {
            seeMore.isVisible = (layoutPosition == 9)
            popular = item
            root.setOnClickListener {
                onItemClickListener?.let { it(item.id) }
            }
            seeMore.setOnClickListener {
                onItemClickListener?.let { it(R.string.popular) } }

            executePendingBindings()
        }
    }
}