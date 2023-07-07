package com.sajjadio.trailers.ui.genres

import android.os.Bundle
import android.view.View
import com.sajjadio.trailers.R
import com.sajjadio.trailers.databinding.FragmentGenresBinding
import com.sajjadio.trailers.ui.base.BaseFragment
import com.sajjadio.trailers.ui.genres.viewModel.GenresViewModel
import com.sajjadio.trailers.utils.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GenresFragment :
    BaseFragment<FragmentGenresBinding, GenresViewModel>(R.layout.fragment_genres) {

    override val LOG_TAG = this::class.java.simpleName
    override val viewModelClass = GenresViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewGenres.adapter = GenresAdapter(emptyList(), viewModel)

        viewModel.clickItemEvent.observeEvent(viewLifecycleOwner) {
            navigateToAnotherDestination(
                GenresFragmentDirections.actionGenresFragmentToMoviesOfGenreFragment(it.first,it.second)
            )
        }
    }
}