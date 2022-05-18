package com.example.movie.ui.fragment.favorite.adapter

import com.example.movie.R
import com.example.movie.data.model.trend.Trending
import com.example.movie.databinding.LayoutItemCardCommenBinding
import com.example.movie.ui.base.adapter.BaseAdapter

class FavoriteAdapter(
    list: MutableList<Trending>
): BaseAdapter<LayoutItemCardCommenBinding, Trending>(list) {

    override val layoutId = R.layout.layout_item_card_commen

    override fun bind(binding: LayoutItemCardCommenBinding, item: Trending) {
        binding.apply {
//            posterPopular.loadImagetesting(item.posterId)
//            titleMS.text = item.title
//            item.type.type.forEach {
//                type.text = "${it}, "
//            }
//            rating.rating = item.rate
//            date.text = "2022"
        }
    }
}