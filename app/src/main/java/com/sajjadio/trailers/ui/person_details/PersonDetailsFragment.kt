package com.sajjadio.trailers.ui.person_details


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sajjadio.trailers.BR
import com.sajjadio.trailers.R
import com.sajjadio.trailers.databinding.FragmentPersonDetailsBinding
import com.sajjadio.trailers.databinding.LayoutBottomSheetBioOfPersonBinding
import com.sajjadio.trailers.databinding.LayoutBottomSheetStoryOfMovieBinding
import com.sajjadio.trailers.ui.base.BaseFragment
import com.sajjadio.trailers.ui.movie_details.MovieDetailsFragmentArgs
import com.sajjadio.trailers.ui.movie_details.utils.MovieDetailsDestinationType
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

            is PersonDetailsDestinationType.BottomSheet -> {
                createBottomSheet(destination)
            }
        }

    }

    private fun createBottomSheet(destination: PersonDetailsDestinationType.BottomSheet) {
        val bottomSheetDialog = view?.context?.let { BottomSheetDialog(it) }
        val binding: LayoutBottomSheetBioOfPersonBinding = DataBindingUtil.inflate(
            LayoutInflater.from(view?.context),
            R.layout.layout_bottom_sheet_bio_of_person,
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

    private fun navigateToAnotherDestination(action: NavDirections) {
        findNavController().navigate(action)
    }

    override fun onPause() {
        super.onPause()
        savedPosition =  binding.recyclerViewPerson.computeVerticalScrollOffset()
    }

    override fun onResume() {
        super.onResume()
        binding.recyclerViewPerson.scrollBy(0, savedPosition)
    }
}
