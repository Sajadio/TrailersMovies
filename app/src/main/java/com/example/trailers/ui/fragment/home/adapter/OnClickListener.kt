package com.example.trailers.ui.fragment.home.adapter

import android.widget.ImageView
import com.example.trailers.data.model.trend.Trending

interface OnClickListener {
    fun playNowItem(id: Int)
    fun category(category: ImageView)
    fun openItem(category: Trending)
    fun popular(popular:Int)

}