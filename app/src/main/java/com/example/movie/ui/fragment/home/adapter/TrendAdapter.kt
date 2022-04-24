package com.example.movie.ui.fragment.home.adapter

import com.example.movie.ui.base.adapter.BaseAdapter
import com.example.movie.R
import com.example.movie.data.m.Trend
import com.example.movie.databinding.LayoutTrendCardItemBinding
import com.example.movie.utils.loadImage

class TrendAdapter (
    list: List<Trend>,
) :BaseAdapter<LayoutTrendCardItemBinding,Trend>(list) {
    override val layoutId = R.layout.layout_trend_card_item

    override fun bind(binding: LayoutTrendCardItemBinding, item: Trend) {
        binding.apply {
            poster.loadImage(item.posterId)
        }
    }

}