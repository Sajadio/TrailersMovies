package com.example.trailers.ui.fragment.trend

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.trailers.R
import com.example.trailers.data.model.movie.trend.TrendMovie
import com.example.trailers.databinding.FragmentTrendBinding
import com.example.trailers.ui.base.BaseFragment
import com.example.trailers.ui.fragment.home.viewModel.HomeViewModel
import com.example.trailers.ui.fragment.trend.adapter.SliderAdapter
import com.example.trailers.utils.NetworkHelper
import com.example.trailers.utils.NetworkStatus
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_trend.*


@AndroidEntryPoint
class TrendFragment : BaseFragment<FragmentTrendBinding>(R.layout.fragment_trend) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var slider: SliderAdapter
    private var position = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NetworkHelper(requireContext()).observe(viewLifecycleOwner) {
            if (it == R.string.connected) {
                initialAdapter()
            }
        }
    }

    private fun initialAdapter() {
        viewModel.responseTrendData.observe(viewLifecycleOwner) { state ->
            stateManagement(state)
            state.data()?.let { data ->
                binding.apply {

                    slider = SliderAdapter(data.results)

                    sliderView.setSliderAdapter(slider)
                    sliderView.setIndicatorAnimation(IndicatorAnimationType.DROP)
                    sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
                    sliderView.startAutoCycle()


                    slider.onItemClickListener {
                        val action = TrendFragmentDirections.actionTrendFragmentToMovieFragment(it)
                        findNavController().navigate(action)
                        viewModel.saveCurrentPosition.postValue(sliderView.currentPagePosition)

                    }
                }
            }
        }

        viewModel.saveCurrentPosition.observe(viewLifecycleOwner) { position ->
            sliderView.currentPagePosition = position
        }

    }

    private fun stateManagement(state: NetworkStatus<TrendMovie?>) {
        if (state is NetworkStatus.Loading)
            binding.shimmer.startShimmer()
        else
            binding.shimmer.stopShimmer()

        binding.shimmer.isVisible = (state is NetworkStatus.Loading)
        binding.sliderView.isVisible = (state is NetworkStatus.Success)
    }

}