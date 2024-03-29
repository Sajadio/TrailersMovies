package com.sajjadio.trailers.ui.home.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import com.sajjadio.trailers.BR
import com.sajjadio.trailers.R
import com.sajjadio.trailers.ui.home.utils.HomeItem
import com.sajjadio.trailers.databinding.*
import com.sajjadio.trailers.domain.model.CommonResult
import com.sajjadio.trailers.domain.model.TrendMovie
import com.sajjadio.trailers.ui.base.BaseAdapter
import com.sajjadio.trailers.ui.base.BaseInteractListener
import com.sajjadio.trailers.utils.Destination
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType

class HomeAdapter(
    private val listener: HomeInteractListener,
    private val popularTitle: String,
    private val topRatedTitle: String,
    private val upcomingTitle: String,
) : BaseAdapter<HomeItem>(listOf(), listener) {

    override var layoutId: Int = 0

    @SuppressLint("NotifyDataSetChanged")
    fun addNestedItem(newItem: List<HomeItem>) {
        setItems(newItem.sortedBy { it.rank })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        layoutId = getLayoutId(viewType)
        return super.onCreateViewHolder(parent, viewType)
    }

    private fun getLayoutId(viewType: Int): Int {
        return when (viewType) {
            LAYOUT_TREND -> LAYOUT_TREND
            LAYOUT_POPULAR -> LAYOUT_POPULAR
            LAYOUT_TOP_RATED -> LAYOUT_TOP_RATED
            LAYOUT_UPCOMING -> LAYOUT_UPCOMING
            else -> throw Exception("unknown view type")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (val currentItem = getItems()[position]) {
            is HomeItem.Trend -> bindTrendItem(holder as ItemViewHolder, currentItem.trend)
            is HomeItem.Popular -> bindPopularItem(holder as ItemViewHolder, currentItem.popular)
            is HomeItem.TopRated -> bindTopRatedItem(holder as ItemViewHolder, currentItem.topRated)
            is HomeItem.Upcoming -> bindUpComingItem(holder as ItemViewHolder, currentItem.upComing)
        }
    }

    private fun bindTrendItem(holder: ItemViewHolder, items: List<TrendMovie>) {
        with((holder.binding as LayoutRecyclerTrendBinding)) {
            sliderView.setSliderAdapter(TrendSliderAdapter(items, listener))
            sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM)
        }
    }

    private fun bindPopularItem(holder: ItemViewHolder, items: List<CommonResult>) {
        with(holder.binding) {
            setVariable(BR.adapter, CommonAdapter(items, listener))
            setVariable(BR.listener, listener)
            setVariable(BR.header, popularTitle)
        }
    }

    private fun bindTopRatedItem(holder: ItemViewHolder, items: List<CommonResult>) {
        with(holder.binding) {
            setVariable(BR.adapter, CommonAdapter(items, listener))
            setVariable(BR.listener, listener)
            setVariable(BR.header, topRatedTitle)
        }
    }

    private fun bindUpComingItem(holder: ItemViewHolder, items: List<CommonResult>) {
        with(holder.binding) {
            setVariable(BR.adapter, CommonAdapter(items, listener))
            setVariable(BR.listener, listener)
            setVariable(BR.header, upcomingTitle)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItems()[position]) {
            is HomeItem.Trend -> LAYOUT_TREND
            is HomeItem.Popular -> LAYOUT_POPULAR
            is HomeItem.TopRated -> LAYOUT_TOP_RATED
            is HomeItem.Upcoming -> LAYOUT_UPCOMING
        }
    }

    @SuppressLint("NonConstantResourceId")
    private companion object {
        const val LAYOUT_TREND = R.layout.layout_recycler_trend
        const val LAYOUT_POPULAR = R.layout.layout_recycler_popular
        const val LAYOUT_TOP_RATED = R.layout.layout_recycler_top_rated
        const val LAYOUT_UPCOMING = R.layout.layout_recycler_upcoming
    }
}

interface HomeInteractListener : BaseInteractListener {
    fun onClickSeeAllPopularItems(popular: Destination)
    fun onClickSeeAllTopRatedItems(topRated: Destination)
    fun onClickSeeAllUpComingItems(upComing: Destination)
    fun onClickWatchNow(id: Int)

}