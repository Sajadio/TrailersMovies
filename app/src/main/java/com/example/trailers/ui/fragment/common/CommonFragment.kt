package com.example.trailers.ui.fragment.common

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trailers.R
import com.example.trailers.databinding.FragmentCommonBinding
import com.example.trailers.ui.base.BaseFragment
import com.example.trailers.ui.fragment.common.adapter.CommonPagingAdapter
import com.example.trailers.ui.fragment.common.vm.CommonViewModel
import com.example.trailers.ui.fragment.home.vm.HomeViewModel
import com.example.trailers.ui.fragment.search.adapter.PagingLoadStateAdapter
import com.example.trailers.utils.movieToDestination
import com.example.trailers.utils.setAsActionBar
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CommonFragment : BaseFragment<FragmentCommonBinding>(R.layout.fragment_common) {

    @Inject
    lateinit var vm: CommonViewModel
    private lateinit var adapter: CommonPagingAdapter
    private val args: CommonFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            activity?.setAsActionBar(toolbar = toolbar,
                isBack = true,
                title = resources.getString(args.id))

            swiperefreshlayout.setOnRefreshListener {
                adapter.refresh()
                swiperefreshlayout.isRefreshing = false
            }
            checkDestinationID()
        }
    }

    private fun checkDestinationID() {
        vm.checkDestination(args.id)
        initialAdapter()
    }


    private fun initialAdapter() {
        adapter = CommonPagingAdapter()

        binding.rcCommon.layoutManager = GridLayoutManager(context, 2)
        vm.responseCommonPagingData.observe(viewLifecycleOwner) { data ->
            lifecycleScope.launch {
                adapter.submitData(data)
            }
        }

        adapter.onItemClickListener { id ->
            id?.let {
                val action = CommonFragmentDirections.actionCommonFragmentToMoiveFragment(id)
                action.movieToDestination(view)
            }
        }

        binding.rcCommon.hasFixedSize()
        loadStateAdapter()
    }

    private fun loadStateAdapter() {
        binding.rcCommon.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PagingLoadStateAdapter { adapter.retry() },
            footer = PagingLoadStateAdapter { adapter.retry() }
        )

        adapter.addLoadStateListener { loadState ->
            val isEmptyList = loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
            showEmptyList(!isEmptyList)

            binding.rcCommon.isVisible = loadState.source.refresh is LoadState.NotLoading
            (loadState.source.refresh is LoadState.Loading).also {
                binding.progressBar.isVisible = it
            }
        }
    }

    private fun showEmptyList(emptyList: Boolean) {
        binding.rcCommon.isVisible = emptyList
    }
}