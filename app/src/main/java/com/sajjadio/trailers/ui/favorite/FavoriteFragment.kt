package com.sajjadio.trailers.ui.favorite

import android.os.Bundle
import android.view.View
import com.sajjadio.trailers.R
import com.sajjadio.trailers.databinding.FragmentFavoriteBinding
import com.sajjadio.trailers.ui.base.BaseFragment
import com.sajjadio.trailers.ui.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, SearchViewModel>(
    R.layout.fragment_favorite
) {
    override val LOG_TAG: String = this::class.java.name
    override val viewModelClass = SearchViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}