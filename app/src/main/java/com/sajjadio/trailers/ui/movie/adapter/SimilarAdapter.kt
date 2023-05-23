package com.sajjadio.trailers.ui.movie.adapter

import com.sajjadio.trailers.R
import com.sajjadio.trailers.data.model.movie.similar.Result
import com.sajjadio.trailers.databinding.LayoutItemSimilarBinding
import com.sajjadio.trailers.ui.base.BaseAdapter

class SimilarAdapter(
    data: List<Result>,
) : BaseAdapter<LayoutItemSimilarBinding, Result>(data) {

    private var onItemClickListener: ((Int?) -> Unit)? = null
    fun onItemClickListener(listener: (Int?) -> Unit) {
        onItemClickListener = listener
    }

    override val layoutId = R.layout.layout_item_similar

    override fun bind(binding: LayoutItemSimilarBinding, item1: Int, item: Result) {
        binding.apply {
            similar = item
            root.setOnClickListener {
                onItemClickListener?.invoke(item.id)
            }
        }
    }

}
