package com.example.trailers.ui.fragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trailers.R
import com.example.trailers.data.loacal.coming.ComingResultEntity
import com.example.trailers.data.loacal.popular.PopularResultEntity
import com.example.trailers.data.loacal.rated.RatedResultEntity
import com.example.trailers.data.loacal.trend.TrendResultEntity
import com.example.trailers.databinding.*
import com.example.trailers.ui.fragment.coming.adapter.UpcomingAdapter
import com.example.trailers.ui.fragment.popular.adapter.PopularAdapter
import com.example.trailers.ui.fragment.home.adapter.holder.*
import com.example.trailers.ui.fragment.rated.adapter.RatedAdapter
import com.example.trailers.utils.MultiViewTypeItem
import com.example.trailers.utils.ViewTypeHome

class ParentAdapter(
    private var data: List<MultiViewTypeItem<Any>>,
    private val listener: OnClickListener,
) : RecyclerView.Adapter<ParentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {

        return when (viewType) {
            TREND -> TrendViewHolder(
                LayoutRvTrendItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            HEADER_VIEW_POPULAR -> HeaderViewPopularHolder(
                HeaderViewPopularBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            POPULAR -> PopularViewHolder(
                LayoutRvPopularBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            HEADER_VIEW_TOP_RATED -> HeaderViewRatedHolder(
                HeaderViewTopRateBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            RATED -> RatedHolder(
                LayoutRvRatedBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            HEADER_VIEW_UPCOMING -> HeaderViewUpComingHolder(
                HeaderViewUpComingBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            UPCOMING -> UPComingHolder(
                LayoutRvUpcomingBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Unknown item type")
        }

    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        when (holder) {
            is TrendViewHolder -> holder.setData(data[position].item as List<TrendResultEntity>)
            is HeaderViewPopularHolder -> bindHeaderViewPopular(holder, position)
            is PopularViewHolder -> holder.setData(data[position].item as List<PopularResultEntity>)
            is HeaderViewRatedHolder -> bindHeaderViewRated(holder, position)
            is RatedHolder -> holder.setData(data[position].item as List<RatedResultEntity>)
            is HeaderViewUpComingHolder -> bindHeaderViewUpComing(holder, position)
            is UPComingHolder -> holder.setData(data[position].item as List<ComingResultEntity>)

        }
    }

    private fun bindHeaderViewPopular(popularHolder: HeaderViewPopularHolder, position: Int) {
        popularHolder.binding.seeAll.setOnClickListener {
            listener.popular(1)
        }
    }


    private fun bindHeaderViewRated(ratedHolder: HeaderViewRatedHolder, position: Int) {
        ratedHolder.binding.seeAll.setOnClickListener {
            listener.popular(1)
        }
    }

    private fun bindHeaderViewUpComing(
        holder: HeaderViewUpComingHolder, position: Int,
    ) {
        holder.binding.seeAll.setOnClickListener {
//            listener.category(1)
        }
    }

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int) = when (data[position].typeHome) {
        ViewTypeHome.TREND.ordinal -> TREND
        ViewTypeHome.HEADER_VIEW_POPULAR.ordinal -> HEADER_VIEW_POPULAR
        ViewTypeHome.POPULAR.ordinal -> POPULAR
        ViewTypeHome.HEADER_VIEW_TOP_RATED.ordinal -> HEADER_VIEW_TOP_RATED
        ViewTypeHome.RATED.ordinal -> RATED
        ViewTypeHome.HEADER_VIEW_UPCOMING.ordinal -> HEADER_VIEW_UPCOMING
        ViewTypeHome.UPCOMING.ordinal -> UPCOMING

        else -> throw IllegalArgumentException("Unknown item type at: $position")

    }

    companion object {
        const val TREND = R.layout.layout_rv_trend_item
        const val HEADER_VIEW_POPULAR = R.layout.header_view_popular
        const val POPULAR = R.layout.layout_rv_popular
        const val HEADER_VIEW_TOP_RATED = R.layout.header_view_top_rate
        const val RATED = R.layout.layout_rv_rated
        const val HEADER_VIEW_UPCOMING = R.layout.header_view_up_coming
        const val UPCOMING = R.layout.layout_rv_upcoming

    }
}
