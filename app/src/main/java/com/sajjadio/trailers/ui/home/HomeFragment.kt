package com.sajjadio.trailers.ui.home

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.sajjadio.trailers.R
import com.sajjadio.trailers.databinding.FragmentHomeBinding
import com.sajjadio.trailers.ui.HomeActivity
import com.sajjadio.trailers.ui.base.BaseFragment
import com.sajjadio.trailers.utils.UiMode
import com.sajjadio.trailers.utils.isNetworkAvailable
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sajjadio.trailers.ui.home.adapter.HomeAdapter
import com.sajjadio.trailers.ui.home.viewModel.HomeViewModel
import com.sajjadio.trailers.utils.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    override val LOG_TAG: String = this::class.java.simpleName
    override val viewModelClass = HomeViewModel::class.java
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkConnection()
        binding.apply {

            swipeRefreshLayout.setOnRefreshListener {
                viewModel?.refreshData()
                checkConnection()
                swipeRefreshLayout.isRefreshing = false
            }

            tryConnection.setOnClickListener {
                checkConnection()
            }
            btnTheme.setOnClickListener {
                setupDialog()
            }
        }
        setupHomeRecyclerView()
        observeEventWhenClickTrendItem()
        observeEventWhenClickItem()
    }

    private fun setupHomeRecyclerView() {
        val adapter = HomeAdapter(viewModel)
        binding.recyclerViewHome.adapter = adapter
        viewModel.responseHomeData.observe(viewLifecycleOwner) {
            it.let { states ->
                states.data?.let { data ->
                    adapter.addNestedItem(data)
                }
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
}