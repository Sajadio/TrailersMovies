package com.example.trailers.ui.fragment.home.adapter

import com.example.trailers.ui.base.adapter.BaseAdapter
import com.example.trailers.R
import com.example.trailers.databinding.LayoutTrendCardItemBinding
import com.example.trailers.data.model.trend.Result

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