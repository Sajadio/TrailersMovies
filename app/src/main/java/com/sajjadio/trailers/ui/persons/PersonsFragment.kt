package com.sajjadio.trailers.ui.persons

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.sajjadio.trailers.R
import com.sajjadio.trailers.databinding.FragmentPersonsBinding
import com.sajjadio.trailers.ui.PagingLoadStateAdapter
import com.sajjadio.trailers.ui.base.BaseFragment
import com.sajjadio.trailers.ui.common.CommonFragmentDirections
import com.sajjadio.trailers.ui.person_details.PersonDetailsFragmentDirections
import com.sajjadio.trailers.utils.movieToDestination
import com.sajjadio.trailers.utils.navigateToAnotherDestination
import com.sajjadio.trailers.utils.observeEvent
import com.sajjadio.trailers.utils.onClickBackButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PersonsFragment :
    BaseFragment<FragmentPersonsBinding, PersonViewModel>(R.layout.fragment_persons) {
    override val LOG_TAG = this::class.java.simpleName
    override val viewModelClass = PersonViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.clickItemEvent.observeEvent(viewLifecycleOwner) {
            navigateToAnotherDestination(
                PersonsFragmentDirections.actionPersonsFragmentToPersonFragment(it)
            )
        }
        onClickBackButton(binding.toolbar)
       binding.recyclerViewPersons.adapter =  PersonsAdapter(emptyList(), viewModel)
    }
}