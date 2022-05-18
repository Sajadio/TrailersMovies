package com.example.movie.ui.fragment.genres.adapter

import com.example.movie.R
import com.example.movie.data.model.trend.Trending
import com.example.movie.databinding.LayoutItemCardCommenBinding
import com.example.movie.ui.base.adapter.BaseAdapter
import com.example.movie.ui.base.adapter.BaseOnClickItem

class GenresAdapter(
    list: List<Trending>,
    private val listener: BaseOnClickItem<Trending>
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
//            popularCard.setOnClickListener {
//                listener.clickedItem(item)
//            }
        }
    }
}