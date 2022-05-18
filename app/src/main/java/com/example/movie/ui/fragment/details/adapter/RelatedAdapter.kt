package com.example.movie.ui.fragment.details.adapter

import com.example.movie.R
import com.example.movie.data.model.trend.Trending
import com.example.movie.databinding.LayoutItemRelatedBinding
import com.example.movie.ui.base.adapter.BaseAdapter

class RelatedAdapter(data: List<Trending>) : BaseAdapter<LayoutItemRelatedBinding, Trending>(data) {

    override val layoutId = R.layout.layout_item_related

    override fun bind(binding: LayoutItemRelatedBinding, item: Trending) {
        binding.apply {
//            posterItem.loadImagetesting(item.posterId)
//            title.text = item.title
        }
    }

}