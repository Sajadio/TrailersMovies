package com.example.movie.ui.fragment.home.adapter

import com.example.movie.data.m.Genres
import com.example.movie.data.m.Trend

interface OnClickListener {
    fun trendItem(trend: Trend)
    fun category(category: Int)
    fun openItem(category: Genres)
    fun popular(popular:Int)

}