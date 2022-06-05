package com.example.trailers.ui.fragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.trailers.R
import com.example.trailers.data.model.movie.common.Common
import com.example.trailers.data.model.movie.trend.TrendMovie
import com.example.trailers.databinding.*
import com.example.trailers.utils.MultiViewTypeItem
import com.example.trailers.utils.NetworkStatus
import com.example.trailers.utils.ViewType

class MultiTypeViewAdapter(
    private val listener: OnClickListener,
) : RecyclerView.Adapter<MultiTypeViewHolder>() {

    val differ = AsyncListDiffer(this, DifferCallbacks)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultiTypeViewHolder {

        return when (viewType) {

            TRENDING -> TrendHolder(
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
            POPULAR -> PopularHolder(
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
    override fun onBindViewHolder(holder: MultiTypeViewHolder, position: Int) {
        val item = differ.currentList[position]
        when (holder) {

            is TrendHolder -> item.item?.data()
                ?.let { holder.setData(listener, it as TrendMovie) }

            is PopularHolder ->
                item.item?.data()
                    ?.let { holder.setData(listener, it as Common) }

            is RatedHolder ->
                item.item?.data()
                    ?.let { holder.setData(listener, it as Common) }

            is UPComingHolder ->
                item.item?.data()
                    ?.let { holder.setData(listener, it as Common) }

            is HeaderViewPopularHolder -> {}
            is HeaderViewRatedHolder -> {}
            is HeaderViewUpComingHolder -> {}
        }
    }

    override fun getItemCount() = differ.currentList.size

    override fun getItemViewType(position: Int) = when (differ.currentList[position].viewType) {
        ViewType.TRENDING.ordinal -> TRENDING

        ViewType.HEADER_VIEW_POPULAR.ordinal -> HEADER_VIEW_POPULAR
        ViewType.POPULAR.ordinal -> POPULAR

        ViewType.HEADER_VIEW_TOP_RATED.ordinal -> HEADER_VIEW_TOP_RATED
        ViewType.RATED.ordinal -> RATED

        ViewType.HEADER_VIEW_UPCOMING.ordinal -> HEADER_VIEW_UPCOMING
        ViewType.UPCOMING.ordinal -> UPCOMING
        else -> throw IllegalArgumentException("Unknown item type at: $position")
    }

    companion object {
        const val TRENDING = R.layout.layout_rv_trend_item

        const val HEADER_VIEW_POPULAR = R.layout.header_view_popular
        const val POPULAR = R.layout.layout_rv_popular

        const val HEADER_VIEW_TOP_RATED = R.layout.header_view_top_rate
        const val RATED = R.layout.layout_rv_rated

        const val HEADER_VIEW_UPCOMING = R.layout.header_view_up_coming
        const val UPCOMING = R.layout.layout_rv_upcoming

    }

    object DifferCallbacks : DiffUtil.ItemCallback<MultiViewTypeItem<NetworkStatus<Any>>>() {
        override fun areItemsTheSame(
            oldItem: MultiViewTypeItem<NetworkStatus<Any>>,
            newItem: MultiViewTypeItem<NetworkStatus<Any>>,
        ) =
            oldItem.item == newItem.item

        override fun areContentsTheSame(
            oldItem: MultiViewTypeItem<NetworkStatus<Any>>,
            newItem: MultiViewTypeItem<NetworkStatus<Any>>,
        ) =
            oldItem == newItem
    }
}
