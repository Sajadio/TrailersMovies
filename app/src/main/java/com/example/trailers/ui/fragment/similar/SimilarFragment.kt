package com.example.trailers.ui.fragment.similar

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.example.trailers.R
import com.example.trailers.databinding.FragmentSimilarBinding
import com.example.trailers.ui.base.BaseFragment
import com.example.trailers.ui.fragment.similar.adapter.SimilarPagingAdapter
import com.example.trailers.ui.fragment.movie.viewModel.MovieViewModel
import com.example.trailers.ui.fragment.search.adapter.PagingLoadStateAdapter
import com.example.trailers.ui.fragment.similar.viewModel.SimilarViewModel
import com.example.trailers.utils.movieToDestination
import com.example.trailers.utils.setAsActionBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SimilarFragment : BaseFragment<FragmentSimilarBinding>(R.layout.fragment_similar) {

    private val viewModel: SimilarViewModel by viewModels()
    private val arg: SimilarFragmentArgs by navArgs()
    lateinit var adapter: SimilarPagingAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.setAsActionBar(
            toolbar = binding.toolbar,
            isBack = true,
            title = resources.getString(R.string.similar))

        viewModel.getSimilarOfMovieByID(arg.iDMovie)
        initialAdapter()

        binding.swiperefreshlayout.apply {
            this.setOnRefreshListener {
                initialAdapter()
                this.isRefreshing = false
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initialAdapter() {
        adapter = SimilarPagingAdapter()
        viewModel.listSimilarOfMovie.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                adapter.submitData(it)
                binding.rcSimilar.adapter = adapter
                binding.rcSimilar.hasFixedSize()
            }
        }

        adapter.onItemClickListener { id ->
            id?.let {
                val action = SimilarFragmentDirections.actionSimilarFragmentToMoiveFragment(id)
                action.movieToDestination(view)
            }
        }

        binding.rcSimilar.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PagingLoadStateAdapter { adapter.retry() },
            footer = PagingLoadStateAdapter { adapter.retry() }
        )

        adapter.addLoadStateListener { loadState ->
            val isEmptyList =
                loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
            showEmptyList(isEmptyList)
            binding.rcSimilar.isVisible = loadState.source.refresh is LoadState.NotLoading
            (loadState.source.refresh is LoadState.Loading).also {
                stateManagement(it)
            }
        }
    }

    private fun stateManagement(state: Boolean) {
        binding.apply {
            if (state)
                shimmer.startShimmer()
            else
                shimmer.stopShimmer()

            shimmer.isVisible = state
            swiperefreshlayout.isVisible = !state
        }

    }

    private fun showEmptyList(emptyList: Boolean) {
        binding.rcSimilar.isVisible = !emptyList
    }
}