package com.example.trailers.ui.fragment.search

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.trailers.R
import com.example.trailers.databinding.FragmentSearchBinding
import com.example.trailers.ui.base.BaseFragment
import com.example.trailers.ui.fragment.search.adapter.PagingLoadStateAdapter
import com.example.trailers.ui.fragment.search.adapter.SearchPagingAdapter
import com.example.trailers.ui.fragment.search.viewModel.SearchViewModel
import com.example.trailers.utils.Constant.TAG
import com.example.trailers.utils.movieToDestination

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search),
    SearchView.OnQueryTextListener {

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var adapter: SearchPagingAdapter
    private lateinit var helper: SnapHelper


    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        helper = LinearSnapHelper()
        binding.apply {

            searchView.setOnQueryTextListener(this@SearchFragment)
            rcSearch.setOnTouchListener { _, _ ->
                hidePartialKeyboard()
            }
            initialAdapter()

            swiperefreshlayout.setOnRefreshListener {
                adapter.refresh()
                swiperefreshlayout.isRefreshing = false
            }

        }

    }

    private fun initialAdapter() {

        adapter = SearchPagingAdapter()
        binding.rcSearch.adapter = adapter
        helper.attachToRecyclerView(binding.rcSearch)
        adapter.onItemClickListener {
            it?.let { id ->
                val action = SearchFragmentDirections.actionSearchFragmentToMoiveFragment(id)
                action.movieToDestination(view)
            }
        }

        binding.rcSearch.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PagingLoadStateAdapter { adapter.retry() },
            footer = PagingLoadStateAdapter { adapter.retry() }
        )

        viewModel.getMoviesSearch.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                it?.let {
                    adapter.submitData(it)
                }
            }
        }

        adapter.addLoadStateListener { loadState ->
            val isEmptyList = loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
            showEmptyList(isEmptyList)

            // Only show the list if refresh succeeds
            binding.rcSearch.isVisible = loadState.source.refresh is LoadState.NotLoading
            // Show loading spinner during initial load or refresh
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

    override fun onQueryTextSubmit(query: String?) = false

    override fun onQueryTextChange(query: String?): Boolean {

        lifecycleScope.launch(Dispatchers.Main) {
            query?.let {
                if (it.length >= 3) {
                    delay(1000L)
                    viewModel.getSearch(it)
                } else {
                    viewModel._getMoviesSearch.postValue(PagingData.empty())
                }
                delay(50L)
                if (it.isEmpty())
                    binding.hintText2.visibility = View.INVISIBLE
            }
        }
        return true
    }

}