package com.example.movie.ui.fragment.popular

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.movie.R
import com.example.movie.databinding.FragmentHomeBinding
import com.example.movie.databinding.FragmentPopularBinding
import com.example.movie.ui.base.BaseFragment
import com.example.movie.utils.setAsActionBar

class PopularFragment : BaseFragment<FragmentPopularBinding>(R.layout.fragment_popular) {


    @SuppressLint("ResourceAsColor")
    override fun initial() {
        (activity as AppCompatActivity?)?.setAsActionBar(binding.toolbar,true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24)
        binding.toolbar.setNavigationOnClickListener { activity!!.onBackPressed() }
        super.onCreateOptionsMenu(menu, inflater)
    }

}