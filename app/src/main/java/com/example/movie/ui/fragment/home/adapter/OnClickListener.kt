package com.example.movie.ui.fragment.home.adapter

import android.widget.ImageView
import com.example.movie.data.model.trend.Trending

interface OnClickListener {
    fun trendItem(trend: Trending)
    fun category(category: ImageView)
    fun openItem(category: Trending)
    fun popular(popular:Int)

}