package com.example.trailers.ui.fragment.details.adapter

import com.example.trailers.R
import com.example.trailers.data.model.trend.Trending
import com.example.trailers.databinding.LayoutItemActorsBinding
import com.example.trailers.ui.base.adapter.BaseAdapter

class ActorsAdapter(data: List<Trending>) : BaseAdapter<LayoutItemActorsBinding, Trending>(data) {

    override val layoutId = R.layout.layout_item_actors

    override fun bind(binding: LayoutItemActorsBinding, item: Trending) {
        binding.apply {
//            actorsPoster.loadImagetesting(item.posterId)
//            title.text = item.title
        }
    }

}