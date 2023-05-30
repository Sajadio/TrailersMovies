package com.sajjadio.trailers.ui.actors

import com.sajjadio.trailers.R
import com.sajjadio.trailers.databinding.FragmentActorsBinding
import com.sajjadio.trailers.ui.base.BaseFragment
import com.sajjadio.trailers.ui.details.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActorsFragment : BaseFragment<FragmentActorsBinding, DetailsViewModel>(R.layout.fragment_actors) {
    override val LOG_TAG: String = this::class.java.simpleName
    override val viewModelClass = DetailsViewModel::class.java
}