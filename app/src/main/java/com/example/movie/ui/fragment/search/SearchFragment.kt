package com.example.movie.ui.fragment.search

import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.R
import com.example.movie.databinding.FragmentSearchBinding
import android.content.Context
import android.util.Log

import android.view.MenuInflater
import androidx.core.view.isVisible

import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.movie.ui.base.BaseFragment
import com.example.movie.ui.fragment.search.adapter.PagingLoadStateAdapter
import com.example.movie.ui.fragment.search.adapter.SearchPagingAdapter
import com.example.movie.ui.fragment.search.vm.SearchViewModel
import com.example.movie.utils.setAsActionBar
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search),
    androidx.appcompat.widget.SearchView.OnQueryTextListener {

    @Inject
    lateinit var vm: SearchViewModel
    lateinit var adapter: SearchPagingAdapter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun initial() {
        (activity as AppCompatActivity?)?.setAsActionBar(binding.toolbar, true)
        initialAdapter()
    }

    private fun initialAdapter() {
        adapter = SearchPagingAdapter()
        binding.rcSearch.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PagingLoadStateAdapter { adapter.retry() },
            footer = PagingLoadStateAdapter { adapter.retry() }
        )

        vm.getMoviesSearch.observe(this) {
            lifecycleScope.launch(Dispatchers.Main) {
                adapter.submitData(it)
                binding.rcSearch.hasFixedSize()
            }
        }

        adapter.addLoadStateListener { loadState ->
            val isEmptyList = loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
            showEmptyList(isEmptyList)

            // Only show the list if refresh succeeds
            binding.rcSearch.isVisible = loadState.source.refresh is LoadState.NotLoading
            // Show loading spinner during initial load or refresh
            (loadState.source.refresh is LoadState.Loading).also {
                binding.progressBar.isVisible = it
            }
        }
    }

    private fun showEmptyList(emptyList: Boolean) {
        binding.rcSearch.isVisible = !emptyList
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_app_bar, menu)

        menu.findItem(R.id.delete).isVisible = false
        menu.findItem(R.id.setting).isVisible = false
        menu.findItem(R.id.movie).isVisible = false
        menu.findItem(R.id.tv).isVisible = false

        val search = menu.findItem(R.id.search)
        val searchView = search?.actionView as? androidx.appcompat.widget.SearchView
        searchView?.queryHint = resources.getString(R.string.search)
        searchView?.setOnQueryTextListener(this)
        searchView?.maxWidth = Integer.MAX_VALUE

        super.onCreateOptionsMenu(menu, inflater)

    }


    override fun onQueryTextSubmit(query: String?) = false

    override fun onQueryTextChange(query: String?): Boolean {
        lifecycleScope.launch(Dispatchers.Main) {
            query?.let {
                if (it.length >= 3) {
                    delay(1000L)
                    vm.getSearch(it)
                }
            }
        }
        return true
    }
}