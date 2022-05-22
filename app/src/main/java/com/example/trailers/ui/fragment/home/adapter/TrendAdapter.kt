package com.example.trailers.ui.fragment.home.adapter

import android.util.Log
import com.example.trailers.ui.base.adapter.BaseAdapter
import com.example.trailers.R
import com.example.trailers.data.loacal.trend.TrendResultEntity
import com.example.trailers.databinding.LayoutTrendCardItemBinding

class TrendAdapter(
    list: List<TrendResultEntity>,
) : BaseAdapter<LayoutTrendCardItemBinding, TrendResultEntity>(list) {
    override val layoutId = R.layout.layout_trend_card_item

    override fun bind(binding: LayoutTrendCardItemBinding, item: TrendResultEntity) {
        binding.apply {
            trend = item
        }
    }

}