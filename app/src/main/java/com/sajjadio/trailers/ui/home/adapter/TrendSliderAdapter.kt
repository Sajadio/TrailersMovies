package com.sajjadio.trailers.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sajjadio.trailers.data.model.movie.trend.TrendResult
import com.sajjadio.trailers.databinding.LayoutTrendCardItemBinding
import com.smarteist.autoimageslider.SliderViewAdapter

class TrendSliderAdapter(
    private val items: List<TrendResult>,
    private val listener: HomeInteractListener
) : SliderViewAdapter<TrendSliderAdapter.SliderHolder>() {

    private var onItemClickListener: ((Int) -> Unit)? = null
    fun onItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

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
        val item = items[position]
        holder.binding.apply {
            trend = item
            root.setOnClickListener {
                onItemClickListener?.let { it(item.id) }
            }
            executePendingBindings()
        }
    }

    override fun getCount() = items.size

    inner class SliderHolder(val binding: LayoutTrendCardItemBinding) : ViewHolder(binding.root)

}