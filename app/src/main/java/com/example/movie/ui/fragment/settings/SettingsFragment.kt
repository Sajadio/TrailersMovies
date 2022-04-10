package com.example.movie.ui.fragment.settings

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.movie.R
import com.example.movie.databinding.FragmentSettingsBinding
import com.example.movie.ui.base.BaseFragment
import com.example.movie.utils.*
import com.example.movie.utils.ThemeHelper.applyTheme


class SettingsFragment : BaseFragment<FragmentSettingsBinding>(R.layout.fragment_settings) {

    override fun initial() {
        (activity as AppCompatActivity?)?.setAsActionBar(binding.toolbar, true)

        binding.containerFavorite.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_settingsFragment_to_favoriteFragment)
        }

        setAnimation()


        binding.containerAppTheme.setOnClickListener {
            setExpandTheme()
        }

        binding.systemDefault.setOnClickListener {
            applyTheme(R.string.systemDefault)
            findNavController().navigate(R.id.action_settingsFragment_self)
            findNavController().popBackStack()
        }
        binding.light.setOnClickListener {
            applyTheme(R.string.light)
            findNavController().navigate(R.id.action_settingsFragment_self)
            findNavController().popBackStack()

        }
        binding.dark.setOnClickListener {
            applyTheme(R.string.dark)
            findNavController().navigate(R.id.action_settingsFragment_self)
            findNavController().popBackStack()
        }
    }

    private fun setExpandTheme() {
        binding.containerAppThemeExpand.expandTheme()
        binding.containerLogout.startCustomAnimation(R.anim.slide_down, 300L)
    }

    private fun setAnimation() {
        binding.containerHistory.startCustomAnimation(R.anim.slide_down, 100L)
        binding.containerFavorite.startCustomAnimation(R.anim.slide_down, 300L)
        binding.containerAppTheme.startCustomAnimation(R.anim.slide_down, 500L)
        binding.containerLogout.startCustomAnimation(R.anim.slide_down, 800L)
    }

}