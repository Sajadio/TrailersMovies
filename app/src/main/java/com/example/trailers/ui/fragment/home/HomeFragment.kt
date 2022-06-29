package com.example.trailers.ui.fragment.home

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.trailers.R
import com.example.trailers.databinding.FragmentHomeBinding
import com.example.trailers.ui.activity.HomeActivity
import com.example.trailers.ui.base.BaseFragment
import com.example.trailers.ui.fragment.home.viewModel.HomeViewModel
import com.example.trailers.ui.fragment.home.viewModel.StorageViewModel
import com.example.trailers.utils.Constant.TAG
import com.example.trailers.utils.NetworkStatus
import com.example.trailers.utils.UiMode
import com.example.trailers.utils.isNetworkAvailable
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

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
    }


    private fun setupDialog() {
        val arrayAdapter =
            ArrayAdapter<String>((activity as HomeActivity),
                android.R.layout.select_dialog_singlechoice)
        arrayAdapter.add(resources.getString(R.string.systemDefault))
        arrayAdapter.add(resources.getString(R.string.light))
        arrayAdapter.add(resources.getString(R.string.dark))

        MaterialAlertDialogBuilder(requireContext())
            .setSingleChoiceItems(arrayAdapter, (activity as HomeActivity).checkCurrentMode()) { dialog, which ->
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