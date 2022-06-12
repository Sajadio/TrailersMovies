package com.example.trailers.ui.fragment.movie.adapter

import com.example.trailers.R
import com.example.trailers.data.model.movie.actorsmovie.Cast
import com.example.trailers.databinding.LayoutItemCardActorsMovieBinding
import com.example.trailers.ui.base.adapter.BaseAdapter

class ActorsMovieAdapter(data: List<Cast>) :
    BaseAdapter<LayoutItemCardActorsMovieBinding, Cast>(data) {

    override val layoutId = R.layout.layout_item_card_actors_movie

    private var onItemClickListener: ((Int?) -> Unit)? = null
    fun onItemClickListener(listener: (Int?) -> Unit) {
        onItemClickListener = listener
    }

    override fun bind(binding: LayoutItemCardActorsMovieBinding, item1: Int, item: Cast) {
        binding.apply {
            cast = item
            root.setOnClickListener {
                onItemClickListener?.let { it(item.id) }
            }

        }
    }

}