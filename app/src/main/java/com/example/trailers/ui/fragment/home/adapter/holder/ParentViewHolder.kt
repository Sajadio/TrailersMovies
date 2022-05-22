package com.example.trailers.ui.fragment.home.adapter.holder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.trailers.data.loacal.popular.PopularResultEntity
import com.example.trailers.data.loacal.rated.RatedResultEntity
import com.example.trailers.data.loacal.coming.ComingResultEntity
import com.example.trailers.data.loacal.playnow.PlayNowResultEntity
import com.example.trailers.databinding.*
import com.example.trailers.ui.fragment.coming.adapter.UpcomingAdapter
import com.example.trailers.ui.fragment.home.adapter.OnClickListener
import com.example.trailers.ui.fragment.home.adapter.PlayNowAdapter
import com.example.trailers.ui.fragment.popular.adapter.PopularAdapter
import com.example.trailers.ui.fragment.rated.adapter.RatedAdapter
import com.yarolegovich.discretescrollview.transform.ScaleTransformer


sealed class ParentViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)

class PlayNowViewHolder(private val binding: LayoutRvPlayNowItemBinding) : ParentViewHolder(binding) {
    fun setData(listener: OnClickListener, trend: List<PlayNowResultEntity>) {
        val adapter = PlayNowAdapter(trend,listener)
        binding.apply {
            rvTrend.adapter = adapter
            rvTrend.setItemTransformer(
                ScaleTransformer.Builder()
                    .setMaxScale(1.0f)
                    .setMinScale(0.8f)
                    .build()
            )
        }
    }

}

class HeaderViewPopularHolder(val binding: HeaderViewPopularBinding) : ParentViewHolder(binding)

class PopularViewHolder(private val binding: LayoutRvPopularBinding) : ParentViewHolder(binding) {
    fun setData(popular: List<PopularResultEntity>) {
        val adapter = PopularAdapter(popular)
        binding.apply {
            recyclerViewPopular.adapter = adapter
        }
    }

}

class HeaderViewRatedHolder(val binding: HeaderViewTopRateBinding) : ParentViewHolder(binding)
class RatedHolder(private val binding: LayoutRvRatedBinding) : ParentViewHolder(binding) {
    fun setData(rated: List<RatedResultEntity>) {
        val adapter = RatedAdapter(rated)
        binding.apply {
            recyclerViewRated.adapter = adapter
        }
    }
}

class HeaderViewUpComingHolder(val binding: HeaderViewUpComingBinding) : ParentViewHolder(binding)

class UPComingHolder(private val binding: LayoutRvUpcomingBinding) : ParentViewHolder(binding) {
    fun setData(coming: List<ComingResultEntity>) {
        val adapter = UpcomingAdapter(coming)
        binding.apply {
            rcComing.adapter = adapter
        }
    }
}