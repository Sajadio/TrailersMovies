package com.sajjadio.trailers.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sajjadio.trailers.R
import com.sajjadio.trailers.databinding.FragmentDetailsBinding
import com.sajjadio.trailers.ui.base.BaseFragment
import com.sajjadio.trailers.ui.details.adapter.MovieDetailsAdapter
import com.sajjadio.trailers.ui.details.utils.MovieDetailsDestinationType
import com.sajjadio.trailers.utils.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsFragment :
    BaseFragment<FragmentDetailsBinding, MovieDetailsViewModel>(R.layout.fragment_details) {

    override val LOG_TAG: String = this::class.java.simpleName
    override val viewModelClass = MovieDetailsViewModel::class.java
    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDetailsRecyclerView()
        observeEventWhenClickItem()
    }

    private fun setupDetailsRecyclerView() {
        binding.recyclerViewDetails.adapter = MovieDetailsAdapter(viewModel).apply {
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

    private fun checkDestinationType(movieDetailsDestinationType: MovieDetailsDestinationType) {
        when (movieDetailsDestinationType) {
            is MovieDetailsDestinationType.PersonItem -> {
                navigateToAnotherDestination(
                    DetailsFragmentDirections.actionDetailsFragmentToPersonFragment(movieDetailsDestinationType.personId)
                )
            }

            is MovieDetailsDestinationType.SimilarItem ->
                navigateToAnotherDestination(
                    DetailsFragmentDirections.actionDetailsFragmentSelf(movieDetailsDestinationType.movieId)
                )

            is MovieDetailsDestinationType.Similar ->
                navigateToAnotherDestination(
                    DetailsFragmentDirections.actionDetailsFragmentToSimilarFragment(args.movieId)
                )

            MovieDetailsDestinationType.Galleries -> {}
            MovieDetailsDestinationType.GalleryItem -> {

            }

            MovieDetailsDestinationType.Persons -> {}
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
}
