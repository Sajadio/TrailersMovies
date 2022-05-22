package com.example.trailers.ui.fragment.movie

import android.content.Context
import android.util.Log
import android.view.*
import com.example.trailers.databinding.FragmentMovieBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.example.trailers.R
import com.example.trailers.ui.base.BaseFragment
import com.example.trailers.ui.fragment.movie.adapter.ActorsAdapter
import com.example.trailers.ui.fragment.movie.adapter.RelatedAdapter
import com.example.trailers.ui.fragment.movie.vm.MovieViewModel
import com.example.trailers.utils.favoriteItem
import com.example.trailers.utils.listChips
import com.example.trailers.utils.setAsActionBar
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class MovieFragment : BaseFragment<FragmentMovieBinding>(R.layout.fragment_movie) {

    @Inject
    lateinit var vm: MovieViewModel
    private val args: MovieFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun initial() {
        (activity as AppCompatActivity?)?.setAsActionBar(binding.toolbar, true)

        binding.apply {
            viewModel = vm
            include.viewModel = vm
            lifecycleOwner = this@MovieFragment
        }

        setGenres()

        vm.getID(args.id)
        binding.include.rvRelated.adapter = RelatedAdapter(emptyList())
        binding.include.rvActors.adapter = ActorsAdapter(emptyList())

        binding.btnFavorite.setOnClickListener {
            binding.btnFavorite.favoriteItem(isFavorite = false)
            binding.btnNotFavorite.favoriteItem(isFavorite = true)
        }
        binding.btnNotFavorite.setOnClickListener {
            binding.btnFavorite.favoriteItem(isFavorite = true)
            binding.btnNotFavorite.favoriteItem(isFavorite = false)
        }
    }

    private fun setGenres() {
        vm.responseData.observe(this) {
            val genres = it.data()?.genres
            var genre = ""
            if (genres != null) {
                for (i in genres) {
                    genre += if (i != genres.last())
                        "${i.name}, "
                    else
                        i.name
                }
            }
            binding.include.generes.text = genre

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        super.onCreateOptionsMenu(menu, inflater)
    }

}