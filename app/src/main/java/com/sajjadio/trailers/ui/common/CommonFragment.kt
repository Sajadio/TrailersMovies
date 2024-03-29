package com.sajjadio.trailers.ui.common

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.sajjadio.trailers.R
import com.sajjadio.trailers.databinding.FragmentCommonBinding
import com.sajjadio.trailers.ui.base.BaseFragment
import com.sajjadio.trailers.ui.PagingLoadStateAdapter
import com.sajjadio.trailers.utils.Destination
import com.sajjadio.trailers.utils.navigateToAnotherDestination
import com.sajjadio.trailers.utils.observeEvent
import com.sajjadio.trailers.utils.onClickBackButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CommonFragment :
    BaseFragment<FragmentCommonBinding, CommonViewModel>(R.layout.fragment_common) {

    override val LOG_TAG = this::class.java.simpleName
    override val viewModelClass = CommonViewModel::class.java
    private lateinit var adapter: CommonPagingAdapter
    private val args: CommonFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            onClickBackButton(appBarLayout.toolbar)
            viewModel?.title = args.destination.name
            checkDestinationID(args.destination)
        }

        observeEventWhenClickItem()
    }

    private fun observeEventWhenClickItem() {
        viewModel.clickItemEvent.observeEvent(viewLifecycleOwner) {
            navigateToAnotherDestination(
                CommonFragmentDirections.actionCommonFragmentToMovieFragment(it)
            )
        }
    }

    private fun checkDestinationID(destinationType: Destination) {
        viewModel.checkDestination(destinationType)
        initialAdapter()
    }


    private fun initialAdapter() {
        adapter = CommonPagingAdapter(viewModel)
        viewModel.responseCommonPagingData.observe(viewLifecycleOwner) { data ->
            lifecycleScope.launch {
                adapter.submitData(data)
            }
        }
        loadStateAdapter()
    }

    private fun loadStateAdapter() {
        binding.recyclerViewCommon.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PagingLoadStateAdapter { adapter.retry() },
            footer = PagingLoadStateAdapter { adapter.retry() }
        )
        adapter.addLoadStateListener { loadState ->
            (loadState.source.refresh is LoadState.Loading).also {
                binding.shimmer.isVisible = it
            }
        }
    }
}