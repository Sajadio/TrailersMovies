package com.sajjadio.trailers.ui.similar

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.sajjadio.trailers.R
import com.sajjadio.trailers.databinding.FragmentSimilarBinding
import com.sajjadio.trailers.ui.base.BaseFragment
import com.sajjadio.trailers.ui.PagingLoadStateAdapter
import com.sajjadio.trailers.ui.common.CommonFragmentDirections
import com.sajjadio.trailers.utils.movieToDestination
import com.sajjadio.trailers.utils.navigateToAnotherDestination
import com.sajjadio.trailers.utils.observeEvent
import com.sajjadio.trailers.utils.onClickBackButton
import com.sajjadio.trailers.utils.setAsActionBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SimilarFragment :
    BaseFragment<FragmentSimilarBinding, SimilarViewModel>(R.layout.fragment_similar) {

    override val LOG_TAG: String = this::class.java.simpleName
    override val viewModelClass = SimilarViewModel::class.java
    lateinit var adapter: SimilarPagingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClickBackButton(binding.appBarLayout.toolbar)
        setupSimilarRecyclerView()
        viewModel.clickItemEvent.observeEvent(viewLifecycleOwner) {
            navigateToAnotherDestination(
                SimilarFragmentDirections.actionSimilarFragmentToDetailsFragment(it)
            )
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupSimilarRecyclerView() {
        adapter = SimilarPagingAdapter(viewModel)
        viewModel.similarOfMovie.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        }
        loadStateAdapter()
    }

    private fun loadStateAdapter() {
        binding.recyclerViewSimilar.adapter = adapter.withLoadStateHeaderAndFooter(
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