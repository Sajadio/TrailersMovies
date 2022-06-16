package com.example.trailers.ui.fragment.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.trailers.R
import com.example.trailers.databinding.FragmentHomeBinding
import com.example.trailers.ui.base.BaseFragment
import com.example.trailers.ui.fragment.home.adapter.MultiTypeViewAdapter
import com.example.trailers.ui.fragment.home.adapter.OnClickListener
import com.example.trailers.ui.fragment.home.vm.HomeViewModel
import com.example.trailers.utils.*
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            viewModel = vm
            lifecycleOwner = viewLifecycleOwner

        }
        checkConnection()
        initialAdapter()
        refresh()
        onClickListener()
    }

    private fun onClickListener() {
        binding.btnSearch.setOnClickListener { view ->
            val action = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
            action.movieToDestination(view)
        }
        binding.btnMenu.setOnClickListener { view ->
            val action = HomeFragmentDirections.actionHomeFragmentToBottomSheet()
            action.movieToDestination(view)
        }
    }

    private fun refresh() {
        binding.swiperefreshlayout.setOnRefreshListener {
            initialAdapter()
            checkConnection()
            binding.swiperefreshlayout.isRefreshing = false
        }
    }

    private fun checkConnection() {
        NetworkHelper(context = requireContext()).observe(viewLifecycleOwner) { state ->
            vm.checkConnection(state.isConnection())
        }
    }

    private fun initialAdapter() {
        vm.responseData.observe(viewLifecycleOwner) { response ->
            adapter = MultiTypeViewAdapter(this@HomeFragment)
            adapter.differ.submitList(response)
            binding.parentRecyclerView.adapter = adapter
        }
    }

    override fun clickItem(id: Int?, destination: Int?) {
        destination?.let {
            val action = HomeFragmentDirections.actionHomeFragmentToCommonFragment(destination)
            action.movieToDestination(view)
        }
        id?.let {
            val action = HomeFragmentDirections.actionHomeFragmentToMoiveFragment(id)
            action.movieToDestination(view)
        }
    }
}