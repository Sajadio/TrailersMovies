package com.example.trailers.ui.fragment.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.trailers.data.model.movie.trend.TrendResult
import com.example.trailers.databinding.LayoutTrendCardItemBinding

class TrendPagingAdapter : PagingDataAdapter<TrendResult, TrendPagingAdapter.TrendHolder>(CharacterComparator) {

    private var onItemClickListener: ((Int?) -> Unit)? = null
    fun onItemClickListener(listener: (Int?) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TrendHolder(
            LayoutTrendCardItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: TrendHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
        }


    inner class TrendHolder(private val binding: LayoutTrendCardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: TrendResult) = apply {
            binding.apply {
                trend = item
                root.setOnClickListener {
                    onItemClickListener?.let { it(item.id) }
                }
            }
        }
    }

    object CharacterComparator : DiffUtil.ItemCallback<TrendResult>() {
        override fun areItemsTheSame(oldItem: TrendResult, newItem: TrendResult) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: TrendResult, newItem: TrendResult) =
            oldItem == newItem
    }

}