package com.example.trailers.ui.fragment.home

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.trailers.R
import com.example.trailers.data.model.HomeItem
import com.example.trailers.databinding.FragmentHomeBinding
import com.example.trailers.ui.activity.HomeActivity
import com.example.trailers.ui.base.BaseFragment
import com.example.trailers.ui.fragment.home.adapter.HomeAdapter
import com.example.trailers.ui.fragment.home.adapter.OnClickListener
import com.example.trailers.ui.fragment.home.viewModel.HomeViewModel
import com.example.trailers.utils.Destination
import com.example.trailers.utils.UiMode
import com.example.trailers.utils.isNetworkAvailable
import com.example.trailers.utils.movieToDestination
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home), OnClickListener {

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkConnection()
        binding.apply {

            swiperefreshlayout.setOnRefreshListener {
                viewModel.refreshData()
                checkConnection()
                swiperefreshlayout.isRefreshing = false
            }

            tryConnection.setOnClickListener {
                checkConnection()
            }
            btnTheme.setOnClickListener {
                setupDialog()
            }
        }
        setupHomeAdapter()
    }

    private fun setupHomeAdapter() {
     val adapter = HomeAdapter(this)
        binding.recyclerViewHome.adapter = adapter
        val nestedItem = mutableListOf<HomeItem>()

        (binding.recyclerViewHome.adapter as HomeAdapter).apply {
            with(viewModel) {
                responseTrendData.observe(viewLifecycleOwner) { state ->
                    state.data()?.let { data ->
                        nestedItem.add(HomeItem.Trend(data.results))
                        this@apply.addNestedItem(nestedItem)
                    }
                }
                responsePopularData.observe(viewLifecycleOwner) { state ->
                    state.data()?.let { data ->
                        nestedItem.add(HomeItem.Popular(data.results))
                        this@apply.addNestedItem(nestedItem)
                    }
                }
                responseRatedData.observe(viewLifecycleOwner) { state ->
                    state.data()?.let { data ->
                        nestedItem.add(HomeItem.TopRated(data.results))
                        this@apply.addNestedItem(nestedItem)
                    }
                }
                responseUpComingData.observe(viewLifecycleOwner) { state ->
                    state.data()?.let { data ->
                        nestedItem.add(HomeItem.Upcoming(data.results))
                        this@apply.addNestedItem(nestedItem)
                    }
                }
            }
        }
    }


    private fun setupDialog() {
        val arrayAdapter =
            ArrayAdapter<String>(
                (activity as HomeActivity),
                android.R.layout.select_dialog_singlechoice
            )
        arrayAdapter.add(resources.getString(R.string.systemDefault))
        arrayAdapter.add(resources.getString(R.string.light))
        arrayAdapter.add(resources.getString(R.string.dark))

        MaterialAlertDialogBuilder(requireContext())
            .setSingleChoiceItems(
                arrayAdapter,
                (activity as HomeActivity).checkCurrentMode()
            ) { dialog, which ->
                when (arrayAdapter.getItem(which)) {
                    resources.getString(R.string.systemDefault) -> setupTheme(UiMode.SYSTEM_DEFAULT)
                    resources.getString(R.string.light) -> setupTheme(UiMode.LIGHT)
                    resources.getString(R.string.dark) -> setupTheme(UiMode.DARK)
                }
                dialog.cancel()
            }.create()
            .show()

    }


    private fun setupTheme(uiMode: UiMode) {
        (activity as HomeActivity).viewModel.changeSelectedTheme(uiMode)
    }

    private fun checkConnection() {
        binding.connection.isVisible = !isNetworkAvailable(requireContext())
    }


    override fun onMoveToMovie(id: Int?) {
        id?.let {
            HomeFragmentDirections.actionHomeFragmentToMoiveFragment(it).movieToDestination(view)
        }
    }

    override fun onSelectedDestination(destination: Destination) {
        when (destination) {
            Destination.Popular -> onMoveToDestination(R.string.popular)
            Destination.TopRated -> onMoveToDestination(R.string.rated)
            Destination.UpComing -> onMoveToDestination(R.string.upComing)
        }
    }

    private fun onMoveToDestination(id: Int) {
        HomeFragmentDirections.actionHomeFragmentToCommonFragment(id).movieToDestination(view)
    }
}