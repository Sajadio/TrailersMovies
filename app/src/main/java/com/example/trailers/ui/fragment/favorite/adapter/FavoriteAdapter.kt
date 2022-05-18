package com.example.trailers.ui.fragment.favorite.adapter

import com.example.trailers.R
import com.example.trailers.data.model.trend.Trending
import com.example.trailers.databinding.LayoutItemCardCommenBinding
import com.example.trailers.ui.base.adapter.BaseAdapter

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