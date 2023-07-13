package com.sajjadio.trailers.ui.favorite

import android.os.Bundle
import android.view.View
import com.sajjadio.trailers.R
import com.sajjadio.trailers.databinding.FragmentFavoriteBinding
import com.sajjadio.trailers.ui.base.BaseFragment
import com.sajjadio.trailers.ui.search.SearchViewModel
import com.sajjadio.trailers.utils.navigateToAnotherDestination
import com.sajjadio.trailers.utils.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>(
    R.layout.fragment_favorite
) {
    override val LOG_TAG: String = this::class.java.name
    override val viewModelClass = FavoriteViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.clickItemEvent.observeEvent(viewLifecycleOwner){
            navigateToAnotherDestination(
                FavoriteFragmentDirections.actionFavoriteFragmentToDetailsFragment(it)
            )
        }

        binding.recyclerViewFavorite.adapter = FavoriteAdapter(emptyList(),viewModel)

    }
}