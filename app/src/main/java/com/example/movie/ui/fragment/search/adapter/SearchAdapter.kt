package com.example.movie.ui.fragment.search.adapter

import com.example.movie.R
import com.example.movie.databinding.LayoutItemCardCommenBinding
import com.example.movie.domain.model.movie.search.Result
import com.example.movie.domain.model.movie.search.Search
import com.example.movie.ui.base.adapter.BaseAdapter
import com.example.movie.utils.loadImage

class SearchAdapter(
    private val items: List<Result>,
) : BaseAdapter<LayoutItemCardCommenBinding, Result>(items) {
    override val layoutId = R.layout.layout_item_card_commen

    override fun bind(binding: LayoutItemCardCommenBinding, item: Result) {
        binding.apply {
            titleMS.text = item.title
            date.text = item.release_date
        }
    }
}