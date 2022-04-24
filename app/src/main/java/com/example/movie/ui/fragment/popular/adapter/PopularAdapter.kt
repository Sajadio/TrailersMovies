package com.example.movie.ui.fragment.popular.adapter

import com.example.movie.R
import com.example.movie.data.m.Genres
import com.example.movie.databinding.LayoutItemCardCommenBinding
import com.example.movie.ui.base.adapter.BaseAdapter
import com.example.movie.ui.base.adapter.BaseOnClickItem
import com.example.movie.utils.loadImage

class PopularAdapter(
    list: MutableList<Genres>,
    private val listener: BaseOnClickItem<Genres>
): BaseAdapter<LayoutItemCardCommenBinding, Genres>(list) {

    override val layoutId = R.layout.layout_item_card_commen

    override fun bind(binding: LayoutItemCardCommenBinding, item: Genres) {
        binding.apply {
            posterPopular.loadImage(item.posterId)
            titleMS.text = item.title
            item.type.type.forEach {
                type.text = "${it}, "
            }
            rating.rating = item.rate
            date.text = "2022"
            popularCard.setOnClickListener {
                listener.clickedItem(item)
            }
        }
    }
}