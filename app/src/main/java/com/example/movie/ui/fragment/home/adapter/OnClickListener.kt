package com.example.movie.ui.fragment.home.adapter

import android.widget.ImageView
import com.example.movie.data.m.Genres
import com.example.movie.data.m.Trend

interface OnClickListener {
    fun trendItem(trend: Trend)
    fun category(category: ImageView)
    fun openItem(category: Genres)
    fun popular(popular:Int)

}