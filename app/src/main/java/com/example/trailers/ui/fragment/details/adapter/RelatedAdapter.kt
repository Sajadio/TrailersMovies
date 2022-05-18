package com.example.trailers.ui.fragment.details.adapter

import com.example.trailers.R
import com.example.trailers.data.model.trend.Trending
import com.example.trailers.databinding.LayoutItemRelatedBinding
import com.example.trailers.ui.base.adapter.BaseAdapter

class RelatedAdapter(data: List<Trending>) : BaseAdapter<LayoutItemRelatedBinding, Trending>(data) {

    override val layoutId = R.layout.layout_item_related

    override fun bind(binding: LayoutItemRelatedBinding, item: Trending) {
        binding.apply {
//            posterItem.loadImagetesting(item.posterId)
//            title.text = item.title
        }
    }

}