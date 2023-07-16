package com.sajjadio.trailers.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.sajjadio.trailers.R
import com.sajjadio.trailers.databinding.FragmentHomeBinding
import com.sajjadio.trailers.ui.base.BaseFragment
import com.sajjadio.trailers.ui.home.adapter.HomeAdapter
import com.sajjadio.trailers.utils.observeEvent
import com.sajjadio.trailers.utils.playVideo
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    override val LOG_TAG: String = this::class.java.simpleName
    override val viewModelClass = HomeViewModel::class.java
    private var savedPosition: Int = 0

    @SuppressLint("ObsoleteSdkInt")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupHomeRecyclerView()
        observeEventWhenClickTrendItem()
        observeEventWhenClickItem()
        observeEventWhenClickWatchNow()
    }

    override fun onPause() {
        super.onPause()
        savedPosition =  binding.recyclerViewHome.computeVerticalScrollOffset()
    }

    override fun onResume() {
        super.onResume()
        binding.recyclerViewHome.scrollBy(0, savedPosition)
    }

    private fun setupHomeRecyclerView() {
        val adapter = HomeAdapter(
            viewModel,
            getString(R.string.popular),
            getString(R.string.top_rated),
            getString(R.string.upcoming)
        )
        binding.recyclerViewHome.adapter = adapter
        viewModel.responseHomeData.observe(viewLifecycleOwner) {
            it.let { data ->
                data?.let { adapter.addNestedItem(data) }
            }
        }
    }

    private fun observeEventWhenClickTrendItem() {
        viewModel.clickItemEvent.observeEvent(viewLifecycleOwner) { id ->
            navigateToAnotherDestination(
                HomeFragmentDirections.actionHomeFragmentToDetailsFragment(id)
            )
        }
    }

    private fun observeEventWhenClickWatchNow() {
        viewModel.videoUrl.observeEvent(viewLifecycleOwner) { url ->
            url?.let { requireActivity().playVideo(url) }
        }
    }

    private fun observeEventWhenClickItem() {
        viewModel.clickShowAllItemEvent.observeEvent(viewLifecycleOwner) {
            navigateToAnotherDestination(
                HomeFragmentDirections.actionHomeFragmentToCommonFragment(it)
            )
        }
    }

    private fun navigateToAnotherDestination(action: NavDirections) {
        findNavController().navigate(action)
    }

}