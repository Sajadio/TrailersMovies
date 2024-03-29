package com.sajjadio.trailers.ui.persons

import android.os.Bundle
import android.view.View
import com.sajjadio.trailers.R
import com.sajjadio.trailers.databinding.FragmentPersonsBinding
import com.sajjadio.trailers.ui.base.BaseFragment
import com.sajjadio.trailers.utils.navigateToAnotherDestination
import com.sajjadio.trailers.utils.observeEvent
import com.sajjadio.trailers.utils.onClickBackButton
import dagger.hilt.android.AndroidEntryPoint

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
        onClickBackButton(binding.appBarLayout.toolbar)
       binding.recyclerViewPersons.adapter =  PersonsAdapter(emptyList(), viewModel)
    }
}