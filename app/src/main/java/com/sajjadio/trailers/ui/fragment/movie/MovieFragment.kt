package com.sajjadio.trailers.ui.fragment.movie

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.sajjadio.trailers.R
import com.sajjadio.trailers.data.model.movie.actors.Actors
import com.sajjadio.trailers.data.model.movie.actors.Cast
import com.sajjadio.trailers.data.model.movie.id.IDMovie
import com.sajjadio.trailers.data.model.movie.similar.Similar
import com.sajjadio.trailers.databinding.FragmentMovieBinding
import com.sajjadio.trailers.ui.base.BaseFragment
import com.sajjadio.trailers.ui.fragment.movie.adapter.ActorsAdapter
import com.sajjadio.trailers.ui.fragment.movie.adapter.SimilarAdapter
import com.sajjadio.trailers.ui.fragment.movie.viewModel.MovieViewModel
import com.sajjadio.trailers.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.layout_item_similar.root


@AndroidEntryPoint
class MovieFragment : BaseFragment<FragmentMovieBinding>(R.layout.fragment_movie) {

    private val viewModel: MovieViewModel by viewModels()

    private val args: MovieFragmentArgs by navArgs()
    private lateinit var actorsAdapter: ActorsAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            activity?.setAsActionBar(toolbar = binding.toolbar, isBack = true)
            initialAdapterActors()
            setUpData()
    }

    private fun setUpData() {
        viewModel.responseData.observe(viewLifecycleOwner) { state ->
            stateManagement(state)
            state?.data()?.let { data ->
                binding.apply {
                    data.backdrop_path?.let {
                        backgroundPath.loadImage(it,
                            Constant.IMAGE_Size_ORIGINAL)
                    }
                    data.poster_path?.let { posterPath.loadImage(it, Constant.IMAGE_Size_500) }
                    collapsingToolbarLayout.title = data.title
                    include.tvTime.text = data.runtime.formatHourMinutes()
                    include.rate.text = data.vote_average.toInt().toString()
                    include.date.text = data.release_date
                    include.description.text = data.overview
                }
            }

        }
    }

    private fun setPlayVideo() {
        viewModel.playVideo.observe(viewLifecycleOwner) {
            it?.data()?.results?.map {
                startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse(Constant.YOUTUBE_BASE + it.key)))
            }
        }
    }


    private fun initialAdapterActors() {
        viewModel.getID(args.id)
        setGenres()
        viewModel.actors.observe(viewLifecycleOwner) { state ->
            stateManagementForActors(state)
            state.data()?.cast?.let { data ->
                actorsAdapter = ActorsAdapter(data)
                binding.include.rvActors.adapter = actorsAdapter
                actorsAdapter.onItemClickListener { data ->
                    getMovieOfActor(data)
                }
                moveToSimilar(state.data()?.id)
            }
        }
        initialAdapterSimilar()
    }

    private fun initialAdapterSimilar() {
        viewModel.similar.observe(viewLifecycleOwner) { state ->
            stateManagementForSimilar(state)
            state?.data()?.results?.let { data ->
                val adapter = SimilarAdapter(data)
                binding.include.rcSimilar.adapter = adapter
            }
        }
    }


    private fun moveToSimilar(id: Int?) {
        binding.include.seeAll.setOnClickListener {
            id?.let {
                val action = MovieFragmentDirections.actionMoiveFragmentToSimilarFragment(id)
                action.movieToDestination(view = this.root)
            }
        }
    }

    private fun getMovieOfActor(cast: Cast) {
        val action = MovieFragmentDirections.actionMoiveFragmentToActorsFragment(cast)
        action.movieToDestination(view = this.root)
    }

    private fun setGenres() {
        viewModel.responseData.observe(viewLifecycleOwner) { state ->
            val genres = state?.data()?.genres
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

    private fun stateManagement(state: NetworkStatus<IDMovie?>) {
        binding.progressBar.isVisible = (state is NetworkStatus.Loading)
        binding.appBarLayout.isVisible = (state is NetworkStatus.Success)
        binding.include.root.isVisible = (state is NetworkStatus.Success)
    }


    private fun stateManagementForActors(state: NetworkStatus<Actors?>) {
        binding.apply {
            if (state is NetworkStatus.Loading)
                include.shimmer.startShimmer()
            else
                include.shimmer.stopShimmer()

            include.shimmer.isVisible = (state is NetworkStatus.Loading)
            include.rvActors.isVisible = (state is NetworkStatus.Success)
        }
    }

    private fun stateManagementForSimilar(state: NetworkStatus<Similar?>) {
        binding.include.rcSimilar.isVisible = (state is NetworkStatus.Success)
    }

}
