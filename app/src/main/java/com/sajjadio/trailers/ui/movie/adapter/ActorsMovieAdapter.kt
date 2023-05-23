package com.sajjadio.trailers.ui.movie.adapter

import com.sajjadio.trailers.R
import com.sajjadio.trailers.data.model.movie.actorsmovie.Cast
import com.sajjadio.trailers.databinding.LayoutItemCardActorsMovieBinding
import com.sajjadio.trailers.ui.base.BaseAdapter

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