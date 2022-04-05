package com.example.movie.ui.fragment.home.adapter

import android.view.ViewGroup
import android.widget.ImageView
import com.example.movie.R
import com.example.movie.data.m.Trend
import com.example.movie.utils.loadImage
import com.opensooq.pluto.base.PlutoAdapter
import com.opensooq.pluto.base.PlutoViewHolder
import com.opensooq.pluto.listeners.OnItemClickListener

class SliderAdapter(
    items: MutableList<Trend>,
    onItemClickListener: OnItemClickListener<Trend>
) : PlutoAdapter<Trend, SliderAdapter.ViewHolder>(items, onItemClickListener) {

    override fun getViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        parent, R.layout.layout_trend_card_item
    )

    class ViewHolder(parent: ViewGroup, itemLayoutId: Int) :
        PlutoViewHolder<Trend>(parent, itemLayoutId) {

        override fun set(item: Trend, position: Int) {
            val img = getView<ImageView>(R.id.poster)
            img.loadImage(item.posterId)
        }
    }
}
