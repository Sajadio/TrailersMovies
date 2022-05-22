package com.example.trailers.ui.fragment.movie.adapter

import com.example.trailers.R
import com.example.trailers.data.model.movie.similar.Result
import com.example.trailers.databinding.LayoutItemRelatedBinding
import com.example.trailers.ui.base.adapter.BaseAdapter

class RelatedAdapter(data: List<Result>) : BaseAdapter<LayoutItemRelatedBinding, Result>(data) {

    override val layoutId = R.layout.layout_item_related

    override fun bind(binding: LayoutItemRelatedBinding, item: Result) {
        binding.apply {
            similar = item
            rating.rating = item.vote_average?.div(2.0f)?.toFloat() ?: 0.0f
        }
    }

}