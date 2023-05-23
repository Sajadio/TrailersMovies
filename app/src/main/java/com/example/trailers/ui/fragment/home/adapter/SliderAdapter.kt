package com.example.trailers.ui.fragment.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.trailers.data.model.movie.trend.TrendResult
import com.example.trailers.databinding.LayoutTrendCardItemBinding
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter(
    private val data: List<TrendResult>,
) : SliderViewAdapter<SliderAdapter.SliderHolder>() {

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
        val item = data[position]
        holder.binding.apply {
            trend = item
            root.setOnClickListener {
                onItemClickListener?.let { it(item.id) }
            }
            executePendingBindings()
        }
    }

    override fun getCount() = data.size

    inner class SliderHolder(val binding: LayoutTrendCardItemBinding) : ViewHolder(binding.root)

}