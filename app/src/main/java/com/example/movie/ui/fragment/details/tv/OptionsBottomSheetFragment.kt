package com.example.movie.ui.fragment.details.tv

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.movie.R
import com.example.movie.data.m.Season
import com.example.movie.databinding.LayoutBottomSheetBinding
import com.example.movie.ui.base.adapter.BaseOnClickItem
import com.example.movie.ui.fragment.details.tv.adapter.SeasonAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class OptionsBottomSheetFragment : BottomSheetDialogFragment(), BaseOnClickItem<Season> {

    private var _binding: LayoutBottomSheetBinding? = null
    private val binding: LayoutBottomSheetBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.layout_bottom_sheet, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    private fun setUpViews() {

        val list = mutableListOf<Season>()
        list.add(Season(1))
        list.add(Season(2))
        list.add(Season(3))
        list.add(Season(4))
        list.add(Season(5))
        list.add(Season(6))
        list.add(Season(7))

        binding.rvSeasons.adapter = SeasonAdapter(list,this)

    }


    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle): OptionsBottomSheetFragment {
            val fragment = OptionsBottomSheetFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun clickedItem(item: Season) {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // for prevent memory leaks
        _binding = null
    }
}