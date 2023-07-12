package com.sajjadio.trailers.ui.genres

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sajjadio.trailers.R
import com.sajjadio.trailers.ui.base.BaseFragment
import com.sajjadio.trailers.utils.*
import com.sajjadio.trailers.databinding.FragmentMoviesOfGenresBinding
import com.sajjadio.trailers.ui.PagingLoadStateAdapter
import com.sajjadio.trailers.ui.common.CommonPagingAdapter
import com.sajjadio.trailers.ui.genres.viewModel.MoviesOfGenresViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MoviesOfGenreFragment :
    BaseFragment<FragmentMoviesOfGenresBinding, MoviesOfGenresViewModel>(R.layout.fragment_movies_of_genres) {

    override val LOG_TAG = this::class.java.simpleName
    override val viewModelClass = MoviesOfGenresViewModel::class.java
    private lateinit var adapter: CommonPagingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialAdapter()
        checkConnection()
        refresh()

        onClickBackButton(binding.appBarLayout.toolbar)

        viewModel.clickItemEvent.observeEvent(viewLifecycleOwner) {
            navigateToAnotherDestination(
                MoviesOfGenreFragmentDirections.actionMoviesOfGenreFragmentToDetailsFragment(it)
            )
        }
    }


    private fun checkConnection() {
        NetworkHelper(context = requireContext()).observe(viewLifecycleOwner) { state ->

        }
    }

    private fun refresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            checkConnection()
            initialAdapter()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun initialAdapter() {
        adapter = CommonPagingAdapter(viewModel)

        val gridLayoutManager = GridLayoutManager(context, 2)
        binding.recyclerViewCommon.layoutManager = gridLayoutManager
        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL


        viewModel.responseListOfMovie.observe(viewLifecycleOwner) { data ->
            lifecycleScope.launch {
                adapter.submitData(data)
            }
        }

        binding.recyclerViewCommon.hasFixedSize()
        loadStateAdapter()
    }

    private fun loadStateAdapter() {
        binding.recyclerViewCommon.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PagingLoadStateAdapter { adapter.retry() },
            footer = PagingLoadStateAdapter { adapter.retry() }
        )

        adapter.addLoadStateListener { loadState ->
            val isEmptyList =
                loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
            showEmptyList(!isEmptyList)

            binding.recyclerViewCommon.isVisible = loadState.source.refresh is LoadState.NotLoading
            (loadState.source.refresh is LoadState.Loading).also {
                stateManagement(it)
            }
        }
    }

    private fun showEmptyList(emptyList: Boolean) {
        binding.recyclerViewCommon.isVisible = emptyList
    }


    private fun stateManagement(state: Boolean) {
        if (state)
            binding.shimmer.startShimmer()
        else
            binding.shimmer.stopShimmer()

        binding.shimmer.isVisible = state
        binding.swipeRefreshLayout.isVisible = !state

    }

}