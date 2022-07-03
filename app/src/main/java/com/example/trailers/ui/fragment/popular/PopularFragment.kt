package com.example.trailers.ui.fragment.popular

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.trailers.R
import com.example.trailers.data.model.movie.common.Common
import com.example.trailers.databinding.FragmentPopularBinding
import com.example.trailers.ui.base.BaseFragment
import com.example.trailers.ui.fragment.home.viewModel.HomeViewModel
import com.example.trailers.ui.fragment.popular.adapter.PopularAdapter
import com.example.trailers.ui.fragment.upcoming.UpComingFragmentDirections
import com.example.trailers.utils.NetworkHelper
import com.example.trailers.utils.NetworkStatus
import com.example.trailers.utils.movieToDestination
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularFragment : BaseFragment<FragmentPopularBinding>(R.layout.fragment_popular) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: PopularAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NetworkHelper(requireContext()).observe(viewLifecycleOwner) {
            if (it == R.string.connected) {
                initialAdapter()
            }
        }
    }

    private fun initialAdapter() {
        viewModel.responsePopularData.observe(viewLifecycleOwner) { state ->
            stateManagement(state)
            state?.data()?.let { data ->
                adapter = PopularAdapter(data.results)
                binding.rcPopular.adapter = adapter
                adapter.onItemClickListener {
                    moveToDestination(it)
                }
            }
        }

    }

    private fun moveToDestination(it: Int) {
        if (it == R.string.popular)
            UpComingFragmentDirections
                .actionPopularFragmentToCommonFragment(it)
                .movieToDestination(view)
        else
            UpComingFragmentDirections
                .actionPopularFragmentToMovieFragment(it)
                .movieToDestination(view)

    }

    private fun stateManagement(state: NetworkStatus<Common?>) {
            if (state is NetworkStatus.Loading)
                binding.shimmer.startShimmer()
            else
                binding.shimmer.stopShimmer()

            binding.shimmer.isVisible = (state is NetworkStatus.Loading)
            binding.rcPopular.isVisible = (state is NetworkStatus.Success)
    }
}

