package com.sajjadio.trailers.ui.search

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearSnapHelper
import com.sajjadio.trailers.R
import com.sajjadio.trailers.databinding.FragmentSearchBinding
import com.sajjadio.trailers.ui.PagingLoadStateAdapter
import com.sajjadio.trailers.ui.base.BaseFragment
import com.sajjadio.trailers.utils.navigateToAnotherDestination
import com.sajjadio.trailers.utils.observeEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchViewModel>(R.layout.fragment_search) {

    override val LOG_TAG: String = this::class.java.simpleName
    override val viewModelClass = SearchViewModel::class.java
    private lateinit var adapter: SearchPagingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEventWhenClickItem()
        hidePartialKeyboard()
        initialAdapter()
        onRefresh()
    }

    private fun onRefresh() {
        binding.apply {
            swipeRefreshLayout.setOnRefreshListener {
                adapter.refresh()
                swipeRefreshLayout.isRefreshing = false
            }

        }
    }

    private fun observeEventWhenClickItem() {
        viewModel.clickItemEvent.observeEvent(viewLifecycleOwner) {
            navigateToAnotherDestination(
                SearchFragmentDirections.actionSearchFragmentToMovieFragment(it)
            )
        }
    }

    private fun initialAdapter() {
        adapter = SearchPagingAdapter(viewModel)
        viewModel.responseSearchMovies.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                it?.let { adapter.submitData(it) }
            }
        }
        loadStateAdapter()
    }

    private fun loadStateAdapter() {
        binding.recyclerViewSearch.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PagingLoadStateAdapter { adapter.retry() },
            footer = PagingLoadStateAdapter { adapter.retry() }
        )

        adapter.addLoadStateListener { loadState ->
            val isEmptyList = loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
            binding.recyclerViewSearch.isVisible = !isEmptyList
            binding.shimmer.isVisible = loadState.source.refresh is LoadState.Loading
            binding.recyclerViewSearch.isVisible = loadState.source.refresh !is LoadState.Loading

            if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached) {
                binding.shimmer.isVisible = false
            }
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    private fun hidePartialKeyboard() {
        binding.recyclerViewSearch.setOnTouchListener { _, _ ->
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view?.windowToken, 0)
        }
    }
}