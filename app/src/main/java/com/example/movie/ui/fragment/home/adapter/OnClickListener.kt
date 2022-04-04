package com.example.movie.ui.fragment.home.adapter

import com.example.movie.data.model.Category
import com.example.movie.data.model.Popular
import com.example.movie.data.model.Trend

interface OnClickListener {
    fun trendItem(trend:Trend)
    fun category(category: Int)
    fun openItem(category: Category)
    fun popular(popular:Int)

}