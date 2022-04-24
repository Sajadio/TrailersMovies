package com.example.movie.ui.fragment.details.adapter

import android.annotation.SuppressLint
import com.example.movie.R
import com.example.movie.data.m.Season
import com.example.movie.databinding.LayoutBottomSheetTvBinding
import com.example.movie.databinding.LayoutItemBottomSheetTvBinding
import com.example.movie.ui.base.adapter.BaseAdapter
import com.example.movie.ui.base.adapter.BaseOnClickItem

class SeasonAdapter(
    list: MutableList<Season>,
    private val listener: BaseOnClickItem<Season>
) :
    BaseAdapter<LayoutBottomSheetTvBinding, Season>(list) {

    override val layoutId = R.layout.layout_bottom_sheet_tv

    @SuppressLint("SetTextI18n")
    override fun bind(binding: LayoutBottomSheetTvBinding, item: Season) {
        binding.apply {
            numberSeason.text = "Season ${item.season_number}"
            numberSeason.setOnClickListener {
                numberSeason.setBackgroundColor(it.resources.getColor(R.color.gryLight))
                viewLineSeason.setBackgroundColor(it.resources.getColor(R.color.redLight))
                listener.clickedItem(item)
            }
        }
    }


}