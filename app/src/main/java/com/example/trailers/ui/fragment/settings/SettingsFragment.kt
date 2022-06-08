package com.example.trailers.ui.fragment.settings

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.trailers.R
import com.example.trailers.databinding.FragmentSettingsBinding
import com.example.trailers.ui.base.BaseFragment
import com.example.trailers.utils.*

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(R.layout.fragment_settings) {


    override fun initial() {
        (activity as AppCompatActivity?)?.setAsActionBar(binding.toolbar, true)

    }


//    private fun refresh() {
//        findNavController().navigate(R.id.action_settingsFragment_self)
//        findNavController().popBackStack()
//    }

}