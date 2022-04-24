package com.example.movie.ui.fragment.details.adapter

import com.example.movie.R
import com.example.movie.data.m.Genres
import com.example.movie.data.m.Season
import com.example.movie.databinding.CustomLayoutDetailsTvBinding
import com.example.movie.databinding.LayoutItemActorsBinding
import com.example.movie.databinding.LayoutItemRelatedBinding
import com.example.movie.ui.base.adapter.BaseAdapter
import com.example.movie.utils.ParentListAdapter
import com.example.movie.utils.loadImage

class ActorsAdapter(data: List<Genres>) : BaseAdapter<LayoutItemActorsBinding, Genres>(data) {

    override val layoutId = R.layout.layout_item_actors

    override fun bind(binding: LayoutItemActorsBinding, item: Genres) {
        binding.apply {
            actorsPoster.loadImage(item.posterId)
            title.text = item.title
        }
    }

}