package com.example.movie.ui.fragment.details.tv

import androidx.navigation.fragment.findNavController
import com.example.movie.R
import com.example.movie.data.model.trend.Trending
import com.example.movie.databinding.LayoutItemBottomSheetTvBinding
import com.example.movie.ui.base.BaseBottomSheet
import com.example.movie.ui.base.adapter.BaseOnClickItem

class BottomSheetTV :
    BaseBottomSheet<LayoutItemBottomSheetTvBinding>(layoutId = R.layout.layout_item_bottom_sheet_tv),
    BaseOnClickItem<Trending> {


    override fun setUpViews() {

//        val list = mutableListOf<Season>()
//        list.add(Season(1))
//        list.add(Season(2))
//        list.add(Season(3))
//        list.add(Season(4))
//        list.add(Season(4))
//        list.add(Season(4))
//        list.add(Season(4))
//        list.add(Season(4))
//        list.add(Season(4))
//        list.add(Season(4))
//        list.add(Season(4))
//        list.add(Season(4))
//        list.add(Season(4))
//        list.add(Season(4))
//        list.add(Season(4))
//        list.add(Season(5))
//        list.add(Season(6))
//        list.add(Season(7))
//        list.add(Season(7))
//        list.add(Season(7))
//        list.add(Season(7))

//        binding.rvSeasons.adapter = SeasonAdapter(list,this)

    }

    override fun clickedItem(item: Trending) {
        findNavController().navigate(R.id.action_optionsBottomSheetFragment_to_tvFragment)
        findNavController().popBackStack()

    }

}