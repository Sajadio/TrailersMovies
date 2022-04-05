package com.example.movie.ui.fragment.settings

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.movie.R
import com.example.movie.databinding.FragmentSearchBinding
import com.example.movie.databinding.FragmentSettingsBinding
import com.example.movie.ui.base.BaseFragment
import com.example.movie.utils.setAsActionBar
import kotlinx.android.synthetic.main.layout_custom_settings.view.*

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(R.layout.fragment_settings) {

    override fun initial() {
        (activity as AppCompatActivity?)?.setAsActionBar(binding.toolbar, true)
        view?.containerFavorite?.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_settingsFragment_to_favoriteFragment)
        }

    }

}