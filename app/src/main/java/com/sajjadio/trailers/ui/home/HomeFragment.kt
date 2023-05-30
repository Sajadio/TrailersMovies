package com.sajjadio.trailers.ui.home

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.sajjadio.trailers.R
import com.sajjadio.trailers.data.model.HomeItem
import com.sajjadio.trailers.databinding.FragmentHomeBinding
import com.sajjadio.trailers.ui.HomeActivity
import com.sajjadio.trailers.ui.base.BaseFragment
import com.sajjadio.trailers.utils.Destination
import com.sajjadio.trailers.utils.UiMode
import com.sajjadio.trailers.utils.isNetworkAvailable
import com.sajjadio.trailers.utils.movieToDestination
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sajjadio.trailers.ui.genres.GenresViewModel
import com.sajjadio.trailers.ui.home.adapter.HomeAdapter
import com.sajjadio.trailers.ui.home.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding,HomeViewModel>(R.layout.fragment_home) {

    override val LOG_TAG = this::class.java.simpleName
    override val viewModelClass = HomeViewModel::class.java
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkConnection()
        binding.apply {

            swiperefreshlayout.setOnRefreshListener {
                viewModel?.refreshData()
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
        val adapter = HomeAdapter(viewModel)
        binding.recyclerViewHome.adapter = adapter

        viewModel.responseHomeData.observe(viewLifecycleOwner) {
            it.let {states ->
                states.data()?.let { data ->
                    adapter.addNestedItem(data)
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


    fun onMoveToMovie(id: Int?) {
        id?.let {
            HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it).movieToDestination(view)
        }
    }

    fun onSelectedDestination(destination: Destination) {
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