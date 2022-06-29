package com.example.trailers.ui.fragment.upcoming.adapter

import androidx.core.view.isVisible
import com.example.trailers.R
import com.example.trailers.data.model.movie.common.CommonResult
import com.example.trailers.databinding.LayoutItemCardUpcomingBinding
import com.example.trailers.ui.base.adapter.BaseAdapter
import com.example.trailers.ui.fragment.home.adapter.OnClickListener

class UpComingAdapter(
    data: List<CommonResult>
) : BaseAdapter<LayoutItemCardUpcomingBinding, CommonResult>(data) {


    override val layoutId = R.layout.layout_item_card_upcoming

    private var onItemClickListener: ((Int) -> Unit)? = null
    fun onItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

    override fun bind(
        binding: LayoutItemCardUpcomingBinding,
        layoutPosition: Int,
        item: CommonResult,
    ) {
        binding.apply {
            seeMore.isVisible = (layoutPosition == 9)
            upcoming = item
            root.setOnClickListener {
                onItemClickListener?.let { it (item.id)}
            }
            seeMore.setOnClickListener {
                onItemClickListener?.let { it (R.string.upComing)}
            }
            executePendingBindings()
        }
    }

}