package com.example.trailers.ui.fragment.home.adapter

import com.example.trailers.ui.base.adapter.BaseAdapter
import com.example.trailers.R
import com.example.trailers.data.loacal.playnow.PlayNowResultEntity
import com.example.trailers.databinding.LayoutPlayNowCardItemBinding
import com.example.trailers.databinding.LayoutRvPlayNowItemBinding

class PlayNowAdapter(
    list: List<PlayNowResultEntity>,
   private val listener: OnClickListener,
) : BaseAdapter<LayoutPlayNowCardItemBinding, PlayNowResultEntity>(list) {
    override val layoutId = R.layout.layout_play_now_card_item

    override fun bind(binding: LayoutPlayNowCardItemBinding, item: PlayNowResultEntity) {
        binding.apply {
            playNow = item
            root.setOnClickListener {
                item.id?.let { it1 -> listener.playNowItem(it1) }
            }
        }
    }

}