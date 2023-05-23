package com.sajjadio.trailers.ui.fragment.common

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.sajjadio.trailers.R
import com.sajjadio.trailers.databinding.FragmentCommonBinding
import com.sajjadio.trailers.ui.base.BaseFragment
import com.sajjadio.trailers.ui.fragment.common.adapter.CommonPagingAdapter
import com.sajjadio.trailers.ui.fragment.common.viewModel.CommonViewModel
import com.sajjadio.trailers.ui.fragment.search.adapter.PagingLoadStateAdapter
import com.sajjadio.trailers.utils.movieToDestination
import com.sajjadio.trailers.utils.setAsActionBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CommonFragment : BaseFragment<FragmentCommonBinding>(R.layout.fragment_common) {

    private val viewModel: CommonViewModel by viewModels()

    private lateinit var adapter: CommonPagingAdapter
    private val args: CommonFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            activity?.setAsActionBar(toolbar = toolbar,
                isBack = true,
                title = resources.getString(args.id))

            swiperefreshlayout.setOnRefreshListener {
                adapter.refresh()
                swiperefreshlayout.isRefreshing = false
            }
            checkDestinationID()
        }
    }

    private fun checkDestinationID() {
        viewModel.checkDestination(args.id)
        initialAdapter()
    }


    private fun initialAdapter() {
        adapter = CommonPagingAdapter()

        binding.rcCommon.layoutManager = GridLayoutManager(context, 2)
        viewModel.responseCommonPagingData.observe(viewLifecycleOwner) { data ->
            lifecycleScope.launch {
                adapter.submitData(data)
            }
        }

        adapter.onItemClickListener { id ->
            id?.let {
                val action = CommonFragmentDirections.actionCommonFragmentToMoiveFragment(id)
                action.movieToDestination(view)
            }
        }

        binding.rcCommon.hasFixedSize()
        loadStateAdapter()
    }

    private fun loadStateAdapter() {
        binding.rcCommon.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PagingLoadStateAdapter { adapter.retry() },
            footer = PagingLoadStateAdapter { adapter.retry() }
        )

        adapter.addLoadStateListener { loadState ->
            val isEmptyList = loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
            showEmptyList(!isEmptyList)

            binding.rcCommon.isVisible = loadState.source.refresh is LoadState.NotLoading
            (loadState.source.refresh is LoadState.Loading).also {
               stateManagement(it)
            }
        }
    }

    private fun showEmptyList(emptyList: Boolean) {
        binding.rcCommon.isVisible = !emptyList
    }

    private fun stateManagement(state: Boolean) {
        if (state)
            binding.shimmer.startShimmer()
        else
            binding.shimmer.stopShimmer()

        binding.shimmer.isVisible = state
        binding.swiperefreshlayout.isVisible = !state

    }
}