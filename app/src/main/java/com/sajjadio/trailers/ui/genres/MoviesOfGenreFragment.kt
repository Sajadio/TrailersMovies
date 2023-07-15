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
import com.sajjadio.trailers.ui.similar.SimilarPagingAdapter
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
        onClickBackButton(binding.appBarLayout.toolbar)
        viewModel.clickItemEvent.observeEvent(viewLifecycleOwner) {
            navigateToAnotherDestination(
                MoviesOfGenreFragmentDirections.actionMoviesOfGenreFragmentToDetailsFragment(it)
            )
        }
    }


    private fun initialAdapter() {
        adapter = CommonPagingAdapter(viewModel)
        viewModel.responseListOfMovie.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                adapter.submitData(it)
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