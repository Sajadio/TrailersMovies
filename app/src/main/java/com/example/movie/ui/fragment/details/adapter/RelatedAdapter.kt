package com.example.movie.ui.fragment.details.adapter

import com.example.movie.R
import com.example.movie.data.m.Genres
import com.example.movie.databinding.LayoutItemRelatedBinding
import com.example.movie.ui.base.adapter.BaseAdapter
import com.example.movie.utils.loadImage

class RelatedAdapter(data: List<Genres>) : BaseAdapter<LayoutItemRelatedBinding, Genres>(data) {

    override val layoutId = R.layout.layout_item_related

    override fun bind(binding: LayoutItemRelatedBinding, item: Genres) {
        binding.apply {
            posterItem.loadImage(item.posterId)
            title.text = item.title
        }
    }

}