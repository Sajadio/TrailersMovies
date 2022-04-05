package com.example.movie.ui.fragment.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.data.m.Trend
import com.example.movie.databinding.LayoutTrendCardItemBinding
import com.example.movie.utils.loadImage

class TrendAdapter (
    private val list: List<Trend>,
) : RecyclerView.Adapter<TrendAdapter.TrendViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendViewHolder {
        return TrendViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.layout_trend_card_item, parent, false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TrendViewHolder, position: Int) {
        val trend = list[position]
        holder.binding.apply {
            poster.loadImage(trend.posterId)
        }
    }

    override fun getItemCount() = list.size


    inner class TrendViewHolder(val binding: LayoutTrendCardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}