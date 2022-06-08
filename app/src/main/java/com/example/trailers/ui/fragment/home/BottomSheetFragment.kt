package com.example.trailers.ui.fragment.home

import android.content.Context
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.example.trailers.R
import com.example.trailers.databinding.LayoutBottomSheetBinding
import com.example.trailers.ui.activity.HomeActivity
import com.example.trailers.ui.base.BaseBottomSheet
import com.example.trailers.ui.fragment.home.vm.StorageViewModel
import com.example.trailers.utils.UiMode
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class BottomSheetFragment :
    BaseBottomSheet<LayoutBottomSheetBinding>(R.layout.layout_bottom_sheet) {

    @Inject
    lateinit var vm: StorageViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun setUpViews() {

        binding.genres.setOnClickListener {
            findNavController().navigate(R.id.action_bottomSheet_to_genresFragment)
        }

        binding.theme.setOnClickListener {
            setupDialog()
        }

    }

    private fun setupDialog() {
        val arrayAdapter =
            ArrayAdapter<String>((activity as HomeActivity),
                android.R.layout.select_dialog_singlechoice)
        arrayAdapter.add(resources.getString(R.string.systemDefault))
        arrayAdapter.add(resources.getString(R.string.light))
        arrayAdapter.add(resources.getString(R.string.dark))
        val checkedItem = -1

        MaterialAlertDialogBuilder(requireContext())
            .setSingleChoiceItems(arrayAdapter, checkedItem) { dialog, which ->
                when (arrayAdapter.getItem(which)) {
                    resources.getString(R.string.systemDefault) -> setupTheme(UiMode.SYSTEM_DEFAULT)
                    resources.getString(R.string.light) -> setupTheme(UiMode.LIGHT)
                    resources.getString(R.string.dark) -> setupTheme(UiMode.DARK)
                }
                dialog.dismiss()
            }.create()
            .show()
    }

    private fun setupTheme(uiMode: UiMode) {
        vm.changeSelectedTheme(uiMode)
    }

}