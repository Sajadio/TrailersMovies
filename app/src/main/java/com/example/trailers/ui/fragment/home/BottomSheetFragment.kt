package com.example.trailers.ui.fragment.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.trailers.R
import com.example.trailers.databinding.LayoutBottomSheetBinding
import com.example.trailers.ui.activity.HomeActivity
import com.example.trailers.ui.fragment.home.vm.StorageViewModel
import com.example.trailers.utils.UiMode
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class BottomSheetFragment : BottomSheetDialogFragment() {

    @Inject
    lateinit var vm: StorageViewModel
    private var _binding: LayoutBottomSheetBinding? = null
    val binding: LayoutBottomSheetBinding get() = _binding!!

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.layout_bottom_sheet, container, false)
        return _binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        MaterialAlertDialogBuilder(requireContext())
            .setSingleChoiceItems(arrayAdapter, -1) { dialog, which ->
                when (arrayAdapter.getItem(which)) {
                    resources.getString(R.string.systemDefault) -> setupTheme(UiMode.SYSTEM_DEFAULT)
                    resources.getString(R.string.light) -> setupTheme(UiMode.LIGHT)
                    resources.getString(R.string.dark) -> setupTheme(UiMode.DARK)
                }
                dialog.cancel()
                findNavController().popBackStack()
            }.create()
            .show()

    }

    private fun setupTheme(uiMode: UiMode) {
        vm.changeSelectedTheme(uiMode)
    }

}