package com.sajjadio.trailers.ui.actors

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.sajjadio.trailers.R
import com.sajjadio.trailers.data.model.movie.actorsmovie.ActorsMovie
import com.sajjadio.trailers.data.model.movie.actorsmovie.Cast
import com.sajjadio.trailers.databinding.FragmentActorsBinding
import com.sajjadio.trailers.ui.base.BaseFragment
import com.sajjadio.trailers.ui.details.DetailsViewModel
import com.sajjadio.trailers.ui.similar.SimilarViewModel
import com.sajjadio.trailers.utils.NetworkStatus
import com.sajjadio.trailers.utils.setAsActionBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActorsFragment : BaseFragment<FragmentActorsBinding,DetailsViewModel>(R.layout.fragment_actors) {

    override val LOG_TAG = this::class.java.simpleName
    override val viewModelClass = DetailsViewModel::class.java
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            activity?.setAsActionBar(
                toolbar = toolbar,
                isBack = true,
            )

            swiperefreshlayout.setOnRefreshListener {
                initialAdapter()
                swiperefreshlayout.isRefreshing = false
            }
        }

        initialAdapter()
    }

    private fun initialAdapter() {
//        viewModel.getMovieOfActor(args.info.id)
        viewModel.actorsOfMovie.observe(viewLifecycleOwner) {
            stateManagement(it)
            it.data()?.let { cast ->
                val adapter = ActorsAdapter(cast, viewModel)

                binding.rvActors.layoutManager = GridLayoutManager(context, 3)
                binding.rvActors.adapter = adapter
                binding.rvActors.hasFixedSize()
            }
        }
    }

    private fun stateManagement(state: NetworkStatus<List<Cast>?>) {
        binding.apply {
            if (state is NetworkStatus.Loading)
                shimmer.startShimmer()
            else
                shimmer.stopShimmer()

            shimmer.isVisible = (state is NetworkStatus.Loading)
            rvActors.isVisible = (state is NetworkStatus.Success)
        }
    }
}