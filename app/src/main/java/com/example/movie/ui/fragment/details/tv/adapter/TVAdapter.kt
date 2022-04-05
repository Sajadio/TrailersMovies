package com.example.movie.ui.fragment.details.tv.adapter

import com.example.movie.R
import com.example.movie.data.m.Season
import com.example.movie.databinding.CustomLayoutDetailsTvBinding
import com.example.movie.ui.base.adapter.BaseAdapter
import com.example.movie.utils.ParentListAdapter

class TVAdapter(data: List<Season>) : BaseAdapter<CustomLayoutDetailsTvBinding, ParentListAdapter>(data) {

    override val layoutId = R.layout.custom_layout_details_tv

    override fun bind(binding: CustomLayoutDetailsTvBinding, itemParent: ParentListAdapter) {
        binding
    }

}