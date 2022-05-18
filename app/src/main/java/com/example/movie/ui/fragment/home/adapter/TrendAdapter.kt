package com.example.movie.ui.fragment.home.adapter

import com.example.movie.ui.base.adapter.BaseAdapter
import com.example.movie.R
import com.example.movie.databinding.LayoutTrendCardItemBinding
import com.example.movie.data.model.trend.Result

class TrendAdapter(
    list: List<Result>,
) : BaseAdapter<LayoutTrendCardItemBinding, Result>(list) {
    override val layoutId = R.layout.layout_trend_card_item

    override fun bind(binding: LayoutTrendCardItemBinding, item: Result) {
        binding.apply {
            trend = item
        }
    }

}