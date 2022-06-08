package com.example.trailers.ui.fragment.home.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.trailers.data.model.movie.common.Common
import com.example.trailers.data.model.movie.trend.TrendMovie
import com.example.trailers.databinding.*
import com.example.trailers.ui.fragment.common.adapter.PopularAdapter
import com.example.trailers.ui.fragment.common.adapter.RatedAdapter
import com.example.trailers.ui.fragment.common.adapter.UpcomingAdapter
import com.yarolegovich.discretescrollview.transform.ScaleTransformer


sealed class MultiTypeViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)

class TrendHolder(val binding: LayoutRvTrendItemBinding) : MultiTypeViewHolder(binding) {
    fun setData(listener: OnClickListener, response: TrendMovie) {
        response.results?.let {
            val adapter = TrendAdapter(it, listener)
            binding.apply {
                rvTrend.adapter = adapter
                rvTrend.setItemTransformer(
                    ScaleTransformer.Builder()
                        .setMaxScale(1.1f)
                        .setMinScale(0.8f)
                        .build()
                )
            }
        }
    }
}

class PopularHolder(private val binding: LayoutRvPopularBinding) :
    MultiTypeViewHolder(binding) {
    fun setData(listener: OnClickListener, response: Common) {
        binding.apply {
            response.results?.let { data ->
                val adapter = PopularAdapter(data, listener)
                binding.apply {
                    recyclerViewPopular.adapter = adapter
                }
            }
        }

    }
}

class RatedHolder(private val binding: LayoutRvRatedBinding) : MultiTypeViewHolder(binding) {
    fun setData(listener: OnClickListener, response: Common) {
        response.results?.let { data ->
            val adapter = RatedAdapter(data, listener)
            binding.apply {
                recyclerViewRated.adapter = adapter
            }
        }
    }
}


class UPComingHolder(private val binding: LayoutRvUpcomingBinding) :
    MultiTypeViewHolder(binding) {
    fun setData(listener: OnClickListener, response: Common) {
        response.results?.let { data ->
            val adapter = UpcomingAdapter(data, listener)
            binding.apply {
                rcComing.adapter = adapter
            }
        }
    }
}

class HeaderViewPopularHolder(val binding: HeaderViewPopularBinding) : MultiTypeViewHolder(binding)
class HeaderViewRatedHolder(val binding: HeaderViewTopRateBinding) : MultiTypeViewHolder(binding)
class HeaderViewUpComingHolder(val binding: HeaderViewUpComingBinding) :
    MultiTypeViewHolder(binding)