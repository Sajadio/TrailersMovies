package com.example.trailers.ui.fragment.home.adapter

import android.annotation.SuppressLint
import com.example.trailers.R
import com.example.trailers.data.model.movie.trend.TrendResult
import com.example.trailers.databinding.LayoutTrendCardItemBinding
import com.example.trailers.ui.base.adapter.BaseAdapter
import java.text.FieldPosition

@SuppressLint("CutPasteId")
class TrendAdapter(
    trend: List<TrendResult>,
    private val listener: OnClickListener,
) : BaseAdapter<LayoutTrendCardItemBinding, TrendResult>(trend) {

    override val layoutId = R.layout.layout_trend_card_item

    override fun bind(binding: LayoutTrendCardItemBinding, layoutPosition: Int, item: TrendResult) {
        binding.apply {
            trend = item
            root.setOnClickListener {
                listener.clickItem(item.id)
            }
            executePendingBindings()
        }
    }

}