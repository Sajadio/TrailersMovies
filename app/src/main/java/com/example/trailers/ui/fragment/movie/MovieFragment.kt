package com.example.trailers.ui.fragment.movie

import android.content.Context
import android.os.Bundle
import android.view.*
import com.example.trailers.databinding.FragmentMovieBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.trailers.R
import com.example.trailers.data.model.movie.actors.Cast
import com.example.trailers.ui.base.BaseFragment
import com.example.trailers.ui.fragment.movie.adapter.ActorsAdapter
import com.example.trailers.ui.fragment.movie.adapter.SimilarAdapter
import com.example.trailers.ui.fragment.movie.vm.MovieViewModel
import com.example.trailers.utils.NetworkStatus
import com.example.trailers.utils.setAsActionBar
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class MovieFragment : BaseFragment<FragmentMovieBinding>(R.layout.fragment_movie) {

    @Inject
    lateinit var vm: MovieViewModel
    private val args: MovieFragmentArgs by navArgs()
    private lateinit var actorsAdapter: ActorsAdapter

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

        binding.include.seeAll.setOnClickListener {
            findNavController().navigate(R.id.action_moiveFragment_to_similarFragment)
        }
        binding.playVideo.setOnClickListener {
            setPlayVideo()
        }

        stateManagement()
        setGenres()
        initialAdapter()
    }

    private fun setPlayVideo() {
        vm.playVideo.observe(this) {
            val bundle = Bundle()
            it.data()?.results?.map {
                bundle.putString("url", it.key)
            }
            findNavController()
                .navigate(R.id.action_moiveFragment_to_videoPlayActivity,
                    bundle,
                    null,
                    null)

        }
    }


    private fun initialAdapter() {
        vm.getID(args.id)
        vm.actors.observe(this) {
            it.data()?.cast?.let { data ->
                actorsAdapter = ActorsAdapter(data)
                binding.include.rvActors.adapter = actorsAdapter
                actorsAdapter.onItemClickListener { data ->
                    getMovieOfActor(data)
                }
            }
        }

        vm.similar.observe(this) {
            it.data()?.results?.let {
                binding.include.rcSimilar.adapter = SimilarAdapter(it)
            }
        }

    }

    private fun getMovieOfActor(cast: Cast) {
        val action = MovieFragmentDirections.actionMoiveFragmentToActorsFragment(cast)
        findNavController().navigate(action)

    }

    private fun setGenres() {
        vm.responseData.observe(this) {
            val genres = it.data()?.genres
            var genre = ""
            if (genres != null) {
                for (i in genres) {
                    genre += if (i != genres.last())
                        "${i.name} | "
                    else
                        i.name
                }
            }
            binding.include.generes.text = genre

        }
    }

    private fun stateManagement() {
        vm.responseData.observe(this) { state ->
            binding.progressBar.isVisible = (state is NetworkStatus.Loading)
        }
    }


}