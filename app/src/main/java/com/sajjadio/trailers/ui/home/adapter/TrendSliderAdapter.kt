package com.sajjadio.trailers.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sajjadio.trailers.databinding.LayoutTrendCardItemBinding
import com.sajjadio.trailers.domain.model.TrendMovie
import com.smarteist.autoimageslider.SliderViewAdapter

class TrendSliderAdapter(
    private val items: List<TrendMovie>,
    private val listener: HomeInteractListener
) : SliderViewAdapter<TrendSliderAdapter.SliderHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): SliderHolder {
        return SliderHolder(
            LayoutTrendCardItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SliderHolder, position: Int) {
        val trend = items[position]
        holder.binding.apply {
            item = trend
            listener = this@TrendSliderAdapter.listener
            executePendingBindings()
        }
    }

    override fun getCount() = items.size

    inner class SliderHolder(val binding: LayoutTrendCardItemBinding) : ViewHolder(binding.root)

}