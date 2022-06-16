package com.example.trailers.ui.fragment.movie

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.trailers.R
import com.example.trailers.data.model.movie.actors.Cast
import com.example.trailers.databinding.FragmentMovieBinding
import com.example.trailers.ui.base.BaseFragment
import com.example.trailers.ui.fragment.movie.adapter.ActorsAdapter
import com.example.trailers.ui.fragment.movie.adapter.SimilarAdapter
import com.example.trailers.ui.fragment.movie.vm.MovieViewModel
import com.example.trailers.utils.*
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            viewModel = vm
            include.viewModel = vm
            binding.lifecycleOwner = viewLifecycleOwner


            activity?.setAsActionBar(
                toolbar = toolbar,
                isBack = true)

            binding.include.seeAll.setOnClickListener {
                findNavController().navigate(R.id.action_moiveFragment_to_similarFragment)
            }
            binding.playVideo.setOnClickListener {
                setPlayVideo()
            }

            checkConnection()
        }
    }

    private fun checkConnection() {
        NetworkHelper(context = requireContext()).observe(viewLifecycleOwner) { state ->
            binding.connection.visibility(state.isConnection())
            if (state.isConnection()) {
                stateManagement()
                setGenres()
                initialAdapter()
            }
        }
    }

    private fun setPlayVideo() {
        vm.playVideo.observe(viewLifecycleOwner) {
            it?.data()?.results?.map {
                startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse(Constant.YOUTUBE_BASE + it.key)))
            }
        }
    }


    private fun initialAdapter() {
        vm.getID(args.id)
        vm.actors.observe(viewLifecycleOwner) {
            it.data()?.cast?.let { data ->
                actorsAdapter = ActorsAdapter(data)
                binding.include.rvActors.adapter = actorsAdapter
                actorsAdapter.onItemClickListener { data ->
                    getMovieOfActor(data)
                }
            }
            setUpSimilarData()
        }
    }

    private fun setUpSimilarData() {
        vm.similar.observe(viewLifecycleOwner) {
            it?.data()?.results?.let {
                binding.include.rcSimilar.adapter = SimilarAdapter(it)
            }
        }
    }


    private fun getMovieOfActor(cast: Cast) {
        val action = MovieFragmentDirections.actionMoiveFragmentToActorsFragment(cast)
        action.movieToDestination(view)
    }

    private fun setGenres() {
        vm.responseData.observe(viewLifecycleOwner) {
            val genres = it?.data()?.genres
            var genre = ""
            if (genres != null) {
                for (i in genres) {
                    genre += if (i != genres.last())
                        "${i.name} | "
                    else
                        i.name
                }
                binding.include.generes.text = genre
            }
        }
    }

    private fun stateManagement() {
        vm.responseData.observe(viewLifecycleOwner) { state ->
            binding.progressBar.isVisible = (state is NetworkStatus.Loading)
        }
    }
}
