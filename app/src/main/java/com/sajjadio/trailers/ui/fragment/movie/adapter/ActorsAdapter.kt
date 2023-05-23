package com.sajjadio.trailers.ui.fragment.movie.adapter

import com.sajjadio.trailers.R
import com.sajjadio.trailers.data.model.movie.actors.Cast
import com.sajjadio.trailers.databinding.LayoutItemActorsBinding
import com.sajjadio.trailers.ui.base.BaseAdapter

class ActorsAdapter(data: List<Cast>) : BaseAdapter<LayoutItemActorsBinding, Cast>(data) {

    override val layoutId = R.layout.layout_item_actors

    private var onItemClickListener: ((Cast) -> Unit)? = null
    fun onItemClickListener(listener: (Cast) -> Unit) {
        onItemClickListener = listener
    }

    override fun bind(binding: LayoutItemActorsBinding, item1: Int, item: Cast) {
        binding.apply {
            actor = item
            actorsPoster.setOnClickListener {
                onItemClickListener?.let { it(item) }
            }

        }
    }

}