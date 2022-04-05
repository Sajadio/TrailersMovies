package com.example.movie.ui.fragment.genres

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.movie.R
import com.example.movie.databinding.FragmentGenresBinding
import com.example.movie.databinding.FragmentSettingsBinding
import com.example.movie.ui.base.BaseFragment
import com.example.movie.utils.setAsActionBar

class GenresFragment : BaseFragment<FragmentGenresBinding>(R.layout.fragment_genres) {

    override fun initial() {
        binding.titleToolbar.text = "Generes"
        (activity as AppCompatActivity?)?.setAsActionBar(binding.toolbar,true)

        binding.movie.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_categoriesFragment_to_moiveFragment)
        }

        binding.tv.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_homeFragment_to_genresFragment)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24)
        binding.toolbar.setNavigationOnClickListener { activity!!.onBackPressed() }
        super.onCreateOptionsMenu(menu, inflater)
    }

}