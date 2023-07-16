package com.sajjadio.trailers.ui.movie_details

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sajjadio.trailers.BR
import com.sajjadio.trailers.R
import com.sajjadio.trailers.databinding.FragmentMovieDetailsBinding
import com.sajjadio.trailers.databinding.LayoutBottomSheetStoryOfMovieBinding
import com.sajjadio.trailers.ui.base.BaseFragment
import com.sajjadio.trailers.ui.movie_details.adapter.MovieDetailsAdapter
import com.sajjadio.trailers.ui.movie_details.utils.MovieDetailsDestinationType
import com.sajjadio.trailers.utils.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailsFragment :
    BaseFragment<FragmentMovieDetailsBinding, MovieDetailsViewModel>(R.layout.fragment_movie_details) {

    override val LOG_TAG: String = this::class.java.simpleName
    override val viewModelClass = MovieDetailsViewModel::class.java
    private val args: MovieDetailsFragmentArgs by navArgs()
    private var savedPosition: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDetailsRecyclerView()
        observeEventWhenClickItem()
        viewModel.bitmap.observe(viewLifecycleOwner) {
            requireActivity().saveImageToStorage(it)
        }
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

    private fun checkDestinationType(destination: MovieDetailsDestinationType) {
        when (destination) {
            is MovieDetailsDestinationType.PersonItem -> {
                navigateToAnotherDestination(
                    MovieDetailsFragmentDirections.actionDetailsFragmentToPersonFragment(
                        destination.personId
                    )
                )
            }

            is MovieDetailsDestinationType.SimilarItem ->
                navigateToAnotherDestination(
                    MovieDetailsFragmentDirections.actionDetailsFragmentSelf(
                        destination.movieId
                    )
                )

            is MovieDetailsDestinationType.Similar ->
                navigateToAnotherDestination(
                    MovieDetailsFragmentDirections.actionDetailsFragmentToSimilarFragment(args.movieId)
                )

            MovieDetailsDestinationType.WatchNowMovie -> {
                viewModel.videoUrl.observe(viewLifecycleOwner) {
                    it?.let { it1 -> requireActivity().playVideo(it1) }
                }
            }

            MovieDetailsDestinationType.Persons -> {
                navigateToAnotherDestination(
                    MovieDetailsFragmentDirections.actionDetailsFragmentToPersonsFragment(args.movieId)
                )
            }

            MovieDetailsDestinationType.BackButton -> findNavController().popBackStack()
            is MovieDetailsDestinationType.BottomSheet -> {
                createBottomSheet(destination)
            }
        }
    }

    private fun createBottomSheet(destination: MovieDetailsDestinationType.BottomSheet) {
        val bottomSheetDialog = view?.context?.let { BottomSheetDialog(it) }
        val binding: LayoutBottomSheetStoryOfMovieBinding = DataBindingUtil.inflate(
            LayoutInflater.from(view?.context),
            R.layout.layout_bottom_sheet_story_of_movie,
            null,
            false
        )
        bottomSheetDialog?.setContentView(binding.root)
        with(binding) {
            setVariable(BR.item, destination.item)
            setVariable(BR.listener, destination.listener)
        }
        bottomSheetDialog?.show()
    }

    override fun onPause() {
        super.onPause()
        savedPosition =  binding.recyclerViewDetails.computeVerticalScrollOffset()
    }

    override fun onResume() {
        super.onResume()
        binding.recyclerViewDetails.scrollBy(0, savedPosition)
    }

}
