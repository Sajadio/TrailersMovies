package com.sajjadio.trailers.ui.search

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.map
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.sajjadio.trailers.R
import com.sajjadio.trailers.databinding.FragmentSearchBinding
import com.sajjadio.trailers.ui.base.BaseFragment
import com.sajjadio.trailers.utils.movieToDestination
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding,SearchViewModel>(R.layout.fragment_search) {

    override val LOG_TAG: String = this::class.java.simpleName
    override val viewModelClass = SearchViewModel::class.java
    private lateinit var adapter: SearchPagingAdapter
    private lateinit var helper: SnapHelper


    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        helper = LinearSnapHelper()
        binding.apply {

            rcSearch.setOnTouchListener { _, _ ->
                hidePartialKeyboard()
            }
            initialAdapter()

            swipeRefreshLayout.setOnRefreshListener {
                adapter.refresh()
                swipeRefreshLayout.isRefreshing = false
            }

        }

    }

    private fun initialAdapter() {

        adapter = SearchPagingAdapter()
        binding.rcSearch.adapter = adapter
        helper.attachToRecyclerView(binding.rcSearch)
        adapter.onItemClickListener {
            it?.let { id ->
                val action = SearchFragmentDirections.actionSearchFragmentToMovieFragment(id)
                action.movieToDestination(view)
            }
        }

        binding.rcSearch.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PagingLoadStateAdapter { adapter.retry() },
            footer = PagingLoadStateAdapter { adapter.retry() }
        )

        viewModel.responseSearchMovies.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                it?.let {
                    adapter.submitData(it)
                }
            }
        }

        adapter.addLoadStateListener { loadState ->
            val isEmptyList = loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
            showEmptyList(isEmptyList)

            binding.rcSearch.isVisible = loadState.source.refresh is LoadState.NotLoading
            (loadState.source.refresh is LoadState.Loading).also {
                stateManagement(it)
            }

        }
    }

    private fun stateManagement(state: Boolean) {
        if (state)
            binding.shimmer.startShimmer()
        else
            binding.shimmer.stopShimmer()

        binding.shimmer.isVisible = state
        binding.rcSearch.isVisible = !state

    }

    private fun hidePartialKeyboard(): Boolean {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private fun showEmptyList(emptyList: Boolean) {
        binding.rcSearch.isVisible = !emptyList
        binding.hintText2.isVisible = emptyList
    }
}