package com.sajjadio.trailers.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sajjadio.trailers.R
import com.sajjadio.trailers.data.model.movie.actors.CastDto
import com.sajjadio.trailers.databinding.FragmentDetailsBinding
import com.sajjadio.trailers.ui.base.BaseFragment
import com.sajjadio.trailers.ui.details.utils.DestinationType
import com.sajjadio.trailers.utils.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsFragment :
    BaseFragment<FragmentDetailsBinding, DetailsViewModel>(R.layout.fragment_details) {

    override val LOG_TAG: String = this::class.java.simpleName
    override val viewModelClass = DetailsViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setGenres()
        setupDetailsRecyclerView()
        observeEventWhenClickItem()
    }

    private fun setupDetailsRecyclerView() {
        binding.recyclerViewDetails.adapter = DetailsAdapter(viewModel).apply {
            viewModel.responseDetailsData.observe(viewLifecycleOwner) {
                it.data?.let { it1 -> addNestedItem(it1) }
            }
        }
    }

    private fun observeEventWhenClickItem() {
        viewModel.clickItemEvent.observeEvent(viewLifecycleOwner) { destinationType ->
            checkDestinationType(destinationType)
        }
    }

    private fun checkDestinationType(destinationType: DestinationType) {
        when (destinationType) {
            is DestinationType.ActorItem ->
                navigateToAnotherDestination(
                    DetailsFragmentDirections.actionDetailsFragmentSelf(destinationType.id)
                )

            is DestinationType.SimilarItem ->
                navigateToAnotherDestination(
                    DetailsFragmentDirections.actionDetailsFragmentSelf(destinationType.id)
                )

            DestinationType.Actors ->
                navigateToAnotherDestination(
                    DetailsFragmentDirections.actionDetailsFragmentToActorsFragment()
                )

            DestinationType.Similar ->
                navigateToAnotherDestination(
                    DetailsFragmentDirections.actionDetailsFragmentToSimilarFragment()
                )
        }
    }

    private fun navigateToAnotherDestination(action: NavDirections) {
        findNavController().navigate(action)
    }

    private fun setPlayVideo() {
        viewModel.playVideo.observe(viewLifecycleOwner) {
            it?.data?.results?.map {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(Constant.YOUTUBE_BASE + it.key)
                    )
                )
            }
        }
    }


    private fun getMovieOfActor(cast: CastDto) {
//        val action = DetailsFragmentDirections.actionMoiveFragmentToActorsFragment(cast)
//        action.movieToDestination(view = this.root)
    }

    private fun setGenres() {
        viewModel.responseData.observe(viewLifecycleOwner) { state ->
            val genres = state?.data?.genres
            var genre = ""
            if (genres != null) {
                for (i in genres) {
                    genre += if (i != genres.last())
                        "${i.name} | "
                    else
                        i.name
                }
            }
        }
    }
}
