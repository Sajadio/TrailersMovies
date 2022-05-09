package com.example.movie.ui.fragment.details.movie

import android.view.*
import com.example.movie.databinding.FragmentMovieBinding
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.R
import com.example.movie.domain.repository.MDBRepo
import com.example.movie.ui.base.BaseFragment
import com.example.movie.ui.fragment.details.adapter.ActorsAdapter
import com.example.movie.ui.fragment.details.adapter.RelatedAdapter
import com.example.movie.utils.favoriteItem
import com.example.movie.utils.listChips
import com.example.movie.utils.setAsActionBar


class MovieFragment : BaseFragment<FragmentMovieBinding>(R.layout.fragment_movie) {

    override fun initial() {
        (activity as AppCompatActivity?)?.setAsActionBar(binding.toolbar, true)

//        binding.include.rvRelated.adapter = RelatedAdapter(MDBRepo.getCategory())
//        binding.include.rvActors.adapter = ActorsAdapter(MDBRepo.getCategory())

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