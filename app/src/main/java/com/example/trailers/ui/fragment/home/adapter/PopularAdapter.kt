package com.example.trailers.ui.fragment.home.adapter


import com.example.trailers.R
import com.example.trailers.data.model.movie.common.CommonResult
import com.example.trailers.databinding.LayoutItemCardPopualrBinding
import com.example.trailers.ui.base.BaseAdapter


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
            popular = item
            root.setOnClickListener {
                onItemClickListener?.let { it(item.id) }
            }
        }
    }
}