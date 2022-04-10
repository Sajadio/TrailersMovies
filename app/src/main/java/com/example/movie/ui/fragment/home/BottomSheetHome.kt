package com.example.movie.ui.fragment.home

import androidx.navigation.fragment.findNavController
import com.example.movie.R
import com.example.movie.databinding.LayoutBottomSheetHomeBinding
import com.example.movie.ui.base.BaseBottomSheet


class BottomSheetHome :
    BaseBottomSheet<LayoutBottomSheetHomeBinding>(layoutId = R.layout.layout_bottom_sheet_home) {

    override fun setUpViews() {
        binding.containerSettings.setOnClickListener {
            findNavController().navigate(R.id.action_bottomSheetHome_to_settingsFragment)
        }

        binding.containerMovie.setOnClickListener {
            findNavController().navigate(R.id.action_bottomSheetHome_to_moiveFragment)
        }

        binding.containerTV.setOnClickListener {
            findNavController().navigate(R.id.action_bottomSheetHome_to_tvFragment)
        }

    }

}