package com.example.trailers.ui.fragment.common

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trailers.R
import com.example.trailers.databinding.FragmentCommonBinding
import com.example.trailers.ui.base.BaseFragment
import com.example.trailers.ui.fragment.common.adapter.CommonPagingAdapter
import com.example.trailers.ui.fragment.home.vm.HomeViewModel
import com.example.trailers.ui.fragment.search.adapter.PagingLoadStateAdapter
import com.example.trailers.utils.setAsActionBar
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.launch
import javax.inject.Inject


class CommonFragment : BaseFragment<FragmentCommonBinding>(R.layout.fragment_common) {

    @Inject
    lateinit var vm: HomeViewModel
    private lateinit var adapter: CommonPagingAdapter
    private val args: CommonFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

    }

    @SuppressLint("ResourceAsColor", "NotifyDataSetChanged")
    override fun initial() {
        (activity as AppCompatActivity?)?.setAsActionBar(binding.toolbar, true)
        binding.swiperefreshlayout.setOnRefreshListener {
            adapter.refresh()
            binding.swiperefreshlayout.isRefreshing = false
        }
        checkDestinationID()
    }

    private fun checkDestinationID() {
        binding.titleToolbar.text = resources.getString(args.id)
        vm.checkDestination(args.id)
        initialAdapter()
    }


    private fun initialAdapter() {
        adapter = CommonPagingAdapter()
        binding.rcPopular.layoutManager = GridLayoutManager(context, 2)
        vm.responseCommonPagingData.observe(this) {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        }
        adapter.onItemClickListener {
            clickItem(it)
        }
            binding.rcPopular.hasFixedSize()
        binding.rcPopular.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PagingLoadStateAdapter { adapter.retry() },
            footer = PagingLoadStateAdapter { adapter.retry() }
        )
        adapter.addLoadStateListener { loadState ->
            val isEmptyList = loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
            showEmptyList(isEmptyList)

            // Only show the list if refresh succeeds
            binding.rcPopular.isVisible = loadState.source.refresh is LoadState.NotLoading
            // Show loading spinner during initial load or refresh
            (loadState.source.refresh is LoadState.Loading).also {
                binding.progressBar.isVisible = it
            }
        }
    }

    private fun showEmptyList(emptyList: Boolean) {
        binding.rcPopular.isVisible = !emptyList
    }


    fun clickItem(id: Int?) {
        val bundle = Bundle()
        id?.let {
            bundle.putInt("id", it)
            findNavController().navigate(R.id.action_commonFragment_to_moiveFragment, bundle)
        }
    }
}