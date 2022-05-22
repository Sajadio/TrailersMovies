package com.example.trailers.ui.fragment.home.adapter

import android.view.ViewGroup
import android.widget.ImageView
import com.example.trailers.R
import com.example.trailers.data.model.trend.Trending
import com.opensooq.pluto.base.PlutoAdapter
import com.opensooq.pluto.base.PlutoViewHolder
import com.opensooq.pluto.listeners.OnItemClickListener

class SliderAdapter(
    items: MutableList<Trending>,
    onItemClickListener: OnItemClickListener<Trending>
) : PlutoAdapter<Trending, SliderAdapter.ViewHolder>(items, onItemClickListener) {


    override fun getViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        parent, R.layout.layout_play_now_card_item
    )

     class ViewHolder(parent: ViewGroup, itemLayoutId: Int) :
        PlutoViewHolder<Trending>(parent, itemLayoutId) {



        override fun set(item: Trending, position: Int) {
            val img = getView<ImageView>(R.id.poster)
//            img.loadImagetesting(item.posterId)
        }
    }

}
