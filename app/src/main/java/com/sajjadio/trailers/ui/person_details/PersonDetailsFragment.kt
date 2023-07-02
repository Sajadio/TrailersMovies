package com.sajjadio.trailers.ui.person_details


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sajjadio.trailers.R
import com.sajjadio.trailers.databinding.FragmentPersonDetailsBinding
import com.sajjadio.trailers.ui.base.BaseFragment
import com.sajjadio.trailers.ui.movie_details.MovieDetailsFragmentArgs
import com.sajjadio.trailers.ui.person_details.adapter.PersonAdapter
import com.sajjadio.trailers.ui.person_details.utils.PersonDetailsDestinationType
import com.sajjadio.trailers.utils.observeEvent
import com.sajjadio.trailers.utils.onClickBackButton
import com.sajjadio.trailers.utils.saveImageToStorage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonDetailsFragment :
    BaseFragment<FragmentPersonDetailsBinding, PersonDetailsViewModel>(R.layout.fragment_person_details) {

    override val LOG_TAG: String = this::class.java.simpleName
    override val viewModelClass = PersonDetailsViewModel::class.java
    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDetailsRecyclerView()
        observeEventWhenClickItem()
        viewModel.bitmap.observe(viewLifecycleOwner) {
            requireActivity().saveImageToStorage(it)
        }
    }

    private fun setupDetailsRecyclerView() {
        binding.recyclerViewPerson.adapter = PersonAdapter(viewModel).apply {
            viewModel.responsePersonDetailsData.observe(viewLifecycleOwner) {
                it.data?.let { it1 -> addNestedItem(it1) }
            }
        }
    }

    private fun observeEventWhenClickItem() {
        viewModel.clickItemEvent.observeEvent(viewLifecycleOwner) { destinationType ->
            checkDestinationType(destinationType)
        }
    }

    private fun checkDestinationType(destination: PersonDetailsDestinationType) {
        when (destination) {
            PersonDetailsDestinationType.Movies -> {
            }

            is PersonDetailsDestinationType.MovieItem -> {
                navigateToAnotherDestination(
                    PersonDetailsFragmentDirections.actionPersonFragmentToDetailsFragment(
                        destination.movieId
                    )
                )
            }

            PersonDetailsDestinationType.BackButton -> {
                findNavController().popBackStack()
            }
        }
    }

    private fun navigateToAnotherDestination(action: NavDirections) {
        findNavController().navigate(action)
    }
}
