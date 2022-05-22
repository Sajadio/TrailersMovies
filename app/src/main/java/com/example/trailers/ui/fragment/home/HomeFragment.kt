package com.example.trailers.ui.fragment.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.trailers.R
import com.example.trailers.data.model.trend.Trending
import com.example.trailers.databinding.FragmentHomeBinding
import com.example.trailers.ui.base.BaseFragment
import com.example.trailers.ui.fragment.home.adapter.ParentAdapter
import com.example.trailers.ui.fragment.home.adapter.OnClickListener
import com.example.trailers.ui.fragment.home.vm.HomeViewModel
import com.example.trailers.utils.NetworkHelper
import com.example.trailers.utils.NetworkStatus
import com.example.trailers.utils.setAsActionBar
import com.example.trailers.utils.setSnackbar
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home), OnClickListener {

    /* Fragment doesn't know any details about how FeatureViewModel instances are created */
    @Inject
    lateinit var vm: HomeViewModel
    private lateinit var adapter: ParentAdapter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun initial() {
        (activity as AppCompatActivity?)?.setAsActionBar(binding.toolbar, false)

        binding.apply {
            viewModel = vm
            lifecycleOwner = this@HomeFragment
        }

        NetworkHelper(context = requireContext()).observe(this) { state ->
            binding.root.setSnackbar(state)
        }

        binding.swiperefreshlayout.setOnRefreshListener {
            initialAdapter()
            binding.swiperefreshlayout.isRefreshing = false
        }


        binding.btnSearch.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }
        vm.requestStatus.observe(this@HomeFragment) { state ->
            loadState(state)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initialAdapter() {
        lifecycleScope.launch {
            withContext(Dispatchers.Main) {
                vm.responseData.observe(this@HomeFragment) {
                    adapter = ParentAdapter(it.sortedBy { it.typeHome }, this@HomeFragment)
                    binding.parentRecyclerView.adapter = adapter
                    binding.parentRecyclerView.setHasFixedSize(true)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }


    private fun <T> loadState(networkStatus: NetworkStatus<T>) {
        when (networkStatus) {
            is NetworkStatus.Loading -> showLoading()
            is NetworkStatus.Success -> {
                initialAdapter()
                hideLading()
            }
            is NetworkStatus.Error -> hideLading()
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.parentRecyclerView.visibility = View.INVISIBLE
    }

    private fun hideLading() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    override fun playNowItem(id: Int) {
        val bundle = Bundle()
        bundle.putInt("id",id)
        findNavController().navigate(R.id.action_homeFragment_to_moiveFragment,bundle)
    }

    override fun category(category: ImageView) {
        findNavController().navigate(R.id.action_homeFragment_to_moiveFragment)
    }

    override fun openItem(category: Trending) {
        Toast.makeText(requireContext(), "view more category", Toast.LENGTH_SHORT).show()
    }

    override fun popular(popular: Int) {
        findNavController().navigate(R.id.action_homeFragment_to_popularFragment)
    }


}