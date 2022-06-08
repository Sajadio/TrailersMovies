package com.example.trailers.ui.fragment.home

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.trailers.R
import com.example.trailers.databinding.FragmentHomeBinding
import com.example.trailers.ui.base.BaseFragment
import com.example.trailers.ui.fragment.home.adapter.MultiTypeViewAdapter
import com.example.trailers.ui.fragment.home.adapter.OnClickListener
import com.example.trailers.ui.fragment.home.vm.HomeViewModel
import com.example.trailers.utils.NetworkHelper
import com.example.trailers.utils.NetworkStatus
import com.example.trailers.utils.setSnackbar
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home), OnClickListener {

    /* Fragment doesn't know any details about how FeatureViewModel instances are created */
    @Inject
    lateinit var vm: HomeViewModel
    private lateinit var adapter: MultiTypeViewAdapter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun initial() {
        binding.apply {
            viewModel = vm
        }

        initialAdapter()
        binding.swiperefreshlayout.setOnRefreshListener {
            initialAdapter()
            binding.swiperefreshlayout.isRefreshing = false
        }

        binding.btnSearch.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }
        binding.btnMenu.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_homeFragment_to_bottomSheet)
        }
    }

    private fun initialAdapter() {
        adapter = MultiTypeViewAdapter(this@HomeFragment)
        vm.responseData.observe(this) { response ->
            adapter.differ.submitList(response)
            binding.parentRecyclerView.adapter = adapter
        }

    }


    override fun clickItem(id: Int?, navigation: Int?) {
        navigation?.let {
            destination(navigation)
        }
        val bundle = Bundle()
        id?.let {
            bundle.putInt("id", it)
            findNavController().navigate(R.id.action_homeFragment_to_moiveFragment, bundle)
        }
    }

    private fun destination(navigation: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToCommonFragment(navigation)
        findNavController().navigate(action)
    }
}