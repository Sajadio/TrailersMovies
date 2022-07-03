package com.example.trailers.ui.fragment.upcoming

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.trailers.R
import com.example.trailers.data.model.movie.common.Common
import com.example.trailers.databinding.FragmentUpcomingBinding
import com.example.trailers.ui.base.BaseFragment
import com.example.trailers.ui.fragment.home.viewModel.HomeViewModel
import com.example.trailers.ui.fragment.upcoming.adapter.UpComingAdapter
import com.example.trailers.utils.NetworkHelper
import com.example.trailers.utils.NetworkStatus
import com.example.trailers.utils.movieToDestination
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpComingFragment : BaseFragment<FragmentUpcomingBinding>(R.layout.fragment_upcoming) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: UpComingAdapter
    private lateinit var helper: SnapHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        helper = LinearSnapHelper()
        NetworkHelper(requireContext()).observe(viewLifecycleOwner) {
            if (it == R.string.connected) {
                initialAdapter()
            }
        }
    }


    private fun initialAdapter() {
        viewModel.responseUpComingData.observe(viewLifecycleOwner) {state->
            stateManagement(state)
            state.data()?.let { data ->

                adapter = UpComingAdapter(data.results)

                binding.rcComing.adapter = adapter
                helper.attachToRecyclerView(binding.rcComing)
                adapter.onItemClickListener {
                    moveToDestination(it)
                }
            }
        }

    }

    private fun moveToDestination(it: Int) {
        if (it == R.string.upComing)
            UpComingFragmentDirections
                .actionUpComingFragmentToCommonFragment(it)
                .movieToDestination(view)
        else
            UpComingFragmentDirections
                .actionUpComingFragmentToMovieFragment(it)
                .movieToDestination(view)
    }

    private fun stateManagement(state: NetworkStatus<Common?>) {
            if (state is NetworkStatus.Loading)
                binding.shimmer.startShimmer()
            else
                binding.shimmer.stopShimmer()

            binding.shimmer.isVisible = (state is NetworkStatus.Loading)
            binding.rcComing.isVisible = (state is NetworkStatus.Success)
    }

}