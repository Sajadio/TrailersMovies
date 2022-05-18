package com.example.movie.ui.fragment.details.adapter

import com.example.movie.R
import com.example.movie.data.model.trend.Trending
import com.example.movie.databinding.LayoutItemActorsBinding
import com.example.movie.ui.base.adapter.BaseAdapter

class ActorsAdapter(data: List<Trending>) : BaseAdapter<LayoutItemActorsBinding, Trending>(data) {

    override val layoutId = R.layout.layout_item_actors

    override fun bind(binding: LayoutItemActorsBinding, item: Trending) {
        binding.apply {
//            actorsPoster.loadImagetesting(item.posterId)
//            title.text = item.title
        }
    }

}