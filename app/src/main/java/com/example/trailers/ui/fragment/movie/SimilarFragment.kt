package com.example.trailers.ui.fragment.movie

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.trailers.R
import com.example.trailers.databinding.FragmentSimilarBinding
import com.example.trailers.ui.activity.MovieActivity
import com.example.trailers.ui.base.BaseFragment
import com.example.trailers.ui.fragment.home.adapter.OnClickListener
import com.example.trailers.ui.fragment.movie.adapter.SimilarPagingAdapter
import com.example.trailers.ui.fragment.movie.vm.MovieViewModel
import com.example.trailers.ui.fragment.search.adapter.PagingLoadStateAdapter
import com.example.trailers.ui.fragment.search.adapter.SearchPagingAdapter
import com.example.trailers.utils.setAsActionBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SimilarFragment : BaseFragment<FragmentSimilarBinding>(R.layout.fragment_similar),
    OnClickListener {
    @Inject
    lateinit var vm: MovieViewModel
    lateinit var adapter: SimilarPagingAdapter
    lateinit var navBar: BottomNavigationView


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun initial() {
        (activity as AppCompatActivity?)?.setAsActionBar(binding.toolbar, true)
        navBar = (activity as MovieActivity?)!!.binding.navigation
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
        adapter = SimilarPagingAdapter(this)
        vm.allSimilar.observe(this@SimilarFragment) {
            lifecycleScope.launch(Dispatchers.Main) {
                adapter.submitData(it)
                binding.rcSimilar.adapter = adapter
                binding.rcSimilar.hasFixedSize()
                adapter.notifyDataSetChanged()
            }
        }

        binding.rcSimilar.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PagingLoadStateAdapter { adapter.retry() },
            footer = PagingLoadStateAdapter { adapter.retry() }
        )

        adapter.addLoadStateListener { loadState ->
            val isEmptyList = loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
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

    override fun onResume() {
        super.onResume()
        navBar.isVisible = false
    }

    override fun onDestroy() {
        super.onDestroy()
        navBar.isVisible = true
    }

    override fun clickItem(id: Int?, navigation: Int?) {
        val bundle = Bundle()
        id?.let {
            bundle.putInt("id",it)
        }
        findNavController().navigate(R.id.action_similarFragment_to_moiveFragment,bundle)
    }

}