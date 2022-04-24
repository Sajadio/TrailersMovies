package com.example.movie.ui.fragment.details.movie

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.movie.databinding.FragmentMovieBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.TransitionInflater
import com.example.movie.R
import com.example.movie.data.repository.Repository
import com.example.movie.databinding.FragmentFavoriteBinding
import com.example.movie.ui.base.BaseFragment
import com.example.movie.ui.fragment.details.adapter.ActorsAdapter
import com.example.movie.ui.fragment.details.adapter.RelatedAdapter
import com.example.movie.utils.addChipView
import com.example.movie.utils.favoriteItem
import com.example.movie.utils.listChips
import com.example.movie.utils.setAsActionBar

import com.google.android.material.chip.Chip


class MovieFragment : BaseFragment<FragmentMovieBinding>(R.layout.fragment_movie) {

    override fun initial() {
        (activity as AppCompatActivity?)?.setAsActionBar(binding.toolbar, true)

        binding.include.rvRelated.adapter = RelatedAdapter(Repository.getCategory())
        binding.include.rvActors.adapter = ActorsAdapter(Repository.getCategory())

        binding.btnFavorite.setOnClickListener {
            binding.btnFavorite.favoriteItem(isFavorite = false)
            binding.btnNotFavorite.favoriteItem(isFavorite = true)
        }
        binding.btnNotFavorite.setOnClickListener {
            binding.btnFavorite.favoriteItem(isFavorite = true)
            binding.btnNotFavorite.favoriteItem(isFavorite = false)
        }

        var generes = ""
        listChips.forEach {
            binding.include.generes.text = "$generes •"
        }


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        super.onCreateOptionsMenu(menu, inflater)
    }

}