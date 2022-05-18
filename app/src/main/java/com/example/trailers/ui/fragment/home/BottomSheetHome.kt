package com.example.trailers.ui.fragment.home

import androidx.navigation.fragment.findNavController
import com.example.trailers.R
import com.example.trailers.databinding.LayoutBottomSheetHomeBinding
import com.example.trailers.ui.base.BaseBottomSheet


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