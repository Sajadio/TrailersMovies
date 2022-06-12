package com.example.trailers.ui.fragment.movie.adapter

import com.example.trailers.R
import com.example.trailers.data.model.movie.similar.Result
import com.example.trailers.databinding.LayoutItemSimilarBinding
import com.example.trailers.ui.base.adapter.BaseAdapter

class SimilarAdapter(
    data: List<Result>,
) : BaseAdapter<LayoutItemSimilarBinding, Result>(data) {

    override val layoutId = R.layout.layout_item_similar

    override fun bind(binding: LayoutItemSimilarBinding, item1: Int, item: Result) {
        binding.apply {
            similar = item
        }
    }

}
