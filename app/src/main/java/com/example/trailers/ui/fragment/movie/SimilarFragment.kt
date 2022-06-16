package com.example.trailers.ui.fragment.movie

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.trailers.R
import com.example.trailers.databinding.FragmentSimilarBinding
import com.example.trailers.ui.base.BaseFragment
import com.example.trailers.ui.fragment.movie.adapter.SimilarPagingAdapter
import com.example.trailers.ui.fragment.movie.vm.MovieViewModel
import com.example.trailers.ui.fragment.search.adapter.PagingLoadStateAdapter
import com.example.trailers.utils.NetworkHelper
import com.example.trailers.utils.isConnection
import com.example.trailers.utils.movieToDestination
import com.example.trailers.utils.setAsActionBar
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SimilarFragment : BaseFragment<FragmentSimilarBinding>(R.layout.fragment_similar) {
    @Inject
    lateinit var vm: MovieViewModel
    lateinit var adapter: SimilarPagingAdapter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.setAsActionBar(
            toolbar = binding.toolbar,
            isBack = true,
            title = resources.getString(R.string.similar))
        checkConnection()


        binding.swiperefreshlayout.apply {
            this.setOnRefreshListener {
                checkConnection()
                this.isRefreshing = false
            }
        }
    }

    private fun checkConnection() {
        NetworkHelper(context = requireContext()).observe(viewLifecycleOwner) { state ->
            initialAdapter()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initialAdapter() {
        adapter = SimilarPagingAdapter()
        vm.allSimilar.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                adapter.submitData(it)
                binding.rcSimilar.adapter = adapter
                binding.rcSimilar.hasFixedSize()
            }
        }

        adapter.onItemClickListener { id ->
            id?.let {
                val action =
                    SimilarFragmentDirections.actionSimilarFragmentToMoiveFragment(id)
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
                binding.progressBar.isVisible = it
            }
        }
    }

    private fun showEmptyList(emptyList: Boolean) {
        binding.rcSimilar.isVisible = !emptyList
    }
}