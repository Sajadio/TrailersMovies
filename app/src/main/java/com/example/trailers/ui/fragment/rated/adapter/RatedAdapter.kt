package com.example.trailers.ui.fragment.rated.adapter

import com.example.trailers.R
import com.example.trailers.data.loacal.rated.RatedResultEntity
import com.example.trailers.databinding.LayoutItemCardRatedBinding
import com.example.trailers.ui.base.adapter.BaseAdapter

class RatedAdapter(
    list: List<RatedResultEntity>,
) : BaseAdapter<LayoutItemCardRatedBinding, RatedResultEntity>(list) {

    override val layoutId = R.layout.layout_item_card_rated

    override fun bind(binding: LayoutItemCardRatedBinding, item: RatedResultEntity) {
        binding.apply {
            rated = item
        }
    }
}