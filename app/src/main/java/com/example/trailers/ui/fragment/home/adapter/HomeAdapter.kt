package com.example.trailers.ui.fragment.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.trailers.R
import com.example.trailers.data.model.HomeItem
import com.example.trailers.data.model.movie.common.CommonResult
import com.example.trailers.data.model.movie.trend.TrendResult
import com.example.trailers.databinding.*
import com.example.trailers.utils.Destination
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations

class HomeAdapter(
    private val listener: OnClickListener
) : RecyclerView.Adapter<HomeAdapter.BaseHomeViewHolder>() {

    private var nestedItem = mutableListOf<HomeItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun addNestedItem(newItem: List<HomeItem>) {
        nestedItem = newItem.toMutableList()
        nestedItem.sortBy { it.priority }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHomeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            LAYOUT_TREND -> {
                val binding = LayoutItemTrendBinding.inflate(layoutInflater, parent, false)
                TrendViewHolder(binding)
            }
            LAYOUT_POPULAR -> {
                val binding = LayoutItemPopularBinding.inflate(layoutInflater, parent, false)
                PopularViewHolder(binding)
            }
            LAYOUT_TOP_RATED -> {
                val binding = LayoutItemTopRatedBinding.inflate(layoutInflater, parent, false)
                TopRatedViewHolder(binding)
            }
            else -> {
                val binding = LayoutItemUpcomingBinding.inflate(layoutInflater, parent, false)
                UpComingViewHolder(binding)
            }
        }
    }

    override fun getItemCount() = nestedItem.size

    override fun onBindViewHolder(holder: BaseHomeViewHolder, position: Int) {
        when (holder) {
            is TrendViewHolder -> holder.bindItem(nestedItem[position].item)
            is PopularViewHolder -> holder.bindItem(nestedItem[position].item)
            is TopRatedViewHolder -> holder.bindItem(nestedItem[position].item)
            is UpComingViewHolder -> holder.bindItem(nestedItem[position].item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (nestedItem[position]) {
            is HomeItem.Trend -> LAYOUT_TREND
            is HomeItem.Popular -> LAYOUT_POPULAR
            is HomeItem.TopRated -> LAYOUT_TOP_RATED
            is HomeItem.Upcoming -> LAYOUT_UPCOMING
        }
    }

    abstract class BaseHomeViewHolder(binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        abstract fun bindItem(item: Any)
    }

    inner class TrendViewHolder(val binding: LayoutItemTrendBinding) :
        BaseHomeViewHolder(binding) {
        override fun bindItem(item: Any) {
            val trend = item as List<TrendResult>
            val slider = SliderAdapter(trend)
            with(binding) {
                sliderView.setSliderAdapter(slider)
                sliderView.setIndicatorAnimation(IndicatorAnimationType.DROP)
                sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
                sliderView.startAutoCycle()
                slider.onItemClickListener {
                    listener.onMoveToMovie(it)
                }
            }
        }
    }

    inner class PopularViewHolder(private val binding: LayoutItemPopularBinding) :
        BaseHomeViewHolder(binding) {
        override fun bindItem(item: Any) {
            val adapter = PopularAdapter(item as List<CommonResult>)
            binding.rcPopular.adapter = adapter
            adapter.onItemClickListener {
                listener.onMoveToMovie(it)
            }
            binding.header.setOnClickListener {
                listener.onSelectedDestination(Destination.Popular)
            }
        }
    }

    inner class TopRatedViewHolder(private val binding: LayoutItemTopRatedBinding) :
        BaseHomeViewHolder(binding) {
        override fun bindItem(item: Any) {
            val adapter = TopRatedAdapter(item as List<CommonResult>)
            binding.rcRated.adapter = adapter
            adapter.onItemClickListener {
                listener.onMoveToMovie(it)
            }
            binding.header.setOnClickListener {
                listener.onSelectedDestination(Destination.TopRated)
            }
        }
    }

    inner class UpComingViewHolder(private val binding: LayoutItemUpcomingBinding) :
        BaseHomeViewHolder(binding) {
        override fun bindItem(item: Any) {
            val adapter = UpComingAdapter(item as List<CommonResult>)
            binding.rcComing.adapter = adapter
            adapter.onItemClickListener {
                listener.onMoveToMovie(it)
            }
            binding.header.setOnClickListener {
                listener.onSelectedDestination(Destination.UpComing)
            }
        }
    }

    private companion object {
        const val LAYOUT_TREND = R.layout.layout_item_trend
        const val LAYOUT_POPULAR = R.layout.layout_item_popular
        const val LAYOUT_TOP_RATED = R.layout.layout_item_top_rated
        const val LAYOUT_UPCOMING = R.layout.layout_item_upcoming
    }
}