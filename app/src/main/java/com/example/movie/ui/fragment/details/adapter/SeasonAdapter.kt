package com.example.movie.ui.fragment.details.adapter

import android.annotation.SuppressLint
import com.example.movie.R
import com.example.movie.data.model.trend.Trending
import com.example.movie.databinding.LayoutBottomSheetTvBinding
import com.example.movie.ui.base.adapter.BaseAdapter
import com.example.movie.ui.base.adapter.BaseOnClickItem

class SeasonAdapter(
    list: MutableList<Trending>,
    private val listener: BaseOnClickItem<Trending>
) :
    BaseAdapter<LayoutBottomSheetTvBinding, Trending>(list) {

    override val layoutId = R.layout.layout_bottom_sheet_tv

    @SuppressLint("SetTextI18n")
    override fun bind(binding: LayoutBottomSheetTvBinding, item: Trending) {
        binding.apply {
//            numberSeason.text = "Season ${item.season_number}"
            numberSeason.setOnClickListener {
                numberSeason.setBackgroundColor(it.resources.getColor(R.color.gryLight))
                viewLineSeason.setBackgroundColor(it.resources.getColor(R.color.redLight))
                listener.clickedItem(item)
            }
        }
    }


}