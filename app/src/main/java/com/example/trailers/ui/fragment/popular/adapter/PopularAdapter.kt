package com.example.trailers.ui.fragment.popular.adapter

import com.example.trailers.R
import com.example.trailers.data.loacal.popular.PopularResultEntity
import com.example.trailers.databinding.LayoutItemCardPopualrBinding
import com.example.trailers.ui.base.adapter.BaseAdapter

class PopularAdapter(
    list: List<PopularResultEntity>,
) : BaseAdapter<LayoutItemCardPopualrBinding, PopularResultEntity>(list) {

    override val layoutId = R.layout.layout_item_card_popualr

    override fun bind(binding: LayoutItemCardPopualrBinding, item: PopularResultEntity) {
        binding.apply {
            popular = item

        }
    }
}