package com.example.trailers.ui.fragment.coming.adapter

import android.util.Log
import com.example.trailers.R
import com.example.trailers.data.loacal.coming.ComingResultEntity
import com.example.trailers.databinding.LayoutItemCardUpcomingBinding
import com.example.trailers.ui.base.adapter.BaseAdapter

class UpcomingAdapter(
    data: List<ComingResultEntity>,
) : BaseAdapter<LayoutItemCardUpcomingBinding, ComingResultEntity>(data) {


    override val layoutId = R.layout.layout_item_card_upcoming

    override fun bind(binding: LayoutItemCardUpcomingBinding, item: ComingResultEntity) {
        binding.apply {
            upcoming = item
        }
    }

}