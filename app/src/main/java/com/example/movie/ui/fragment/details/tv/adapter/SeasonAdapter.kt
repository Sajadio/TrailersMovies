package com.example.movie.ui.fragment.details.tv.adapter

import android.util.Log
import com.example.movie.R
import com.example.movie.data.m.Season
import com.example.movie.databinding.LayoutBottomSheetSeasonBinding
import com.example.movie.ui.base.adapter.BaseAdapter
import com.example.movie.ui.base.adapter.BaseOnClickItem

class SeasonAdapter(
    private val list: MutableList<Season>,
    private val listener:BaseOnClickItem<Season>
    ) :
    BaseAdapter<LayoutBottomSheetSeasonBinding,Season>(list) {

    override val layoutId =  R.layout.layout_bottom_sheet_season

    override fun bind(binding: LayoutBottomSheetSeasonBinding, item: Season) {
        binding.apply {
            numberSeason.text =  "Season ${item.season_number}"
            numberSeason.setOnClickListener{
                viewLineSeason.setBackgroundColor(it.resources.getColor(R.color.redLight))
                listener.clickedItem(item)
            }
        }
    }


}