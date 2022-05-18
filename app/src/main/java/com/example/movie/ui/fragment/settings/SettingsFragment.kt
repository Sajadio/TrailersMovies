package com.example.movie.ui.fragment.settings

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.movie.R
import com.example.movie.databinding.FragmentSettingsBinding
import com.example.movie.ui.base.BaseFragment
import com.example.movie.utils.*

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(R.layout.fragment_settings) {

    private lateinit var sharedPref: MovieSharedPreferences

    override fun initial() {

        sharedPref = MovieSharedPreferences((activity as AppCompatActivity).getSharedPreferences(
            Constant.PREFERENCE_NAME,
            Context.MODE_PRIVATE))
        (activity as AppCompatActivity?)?.setAsActionBar(binding.toolbar, true)

        binding.containerFavorite.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_settingsFragment_to_favoriteFragment)
        }
        setAnimation()

        binding.containerAppTheme.setOnClickListener {
            setExpandTheme()
        }

        binding.systemDefault.setOnClickListener {
            sharedPref.updateThemeUI(resources.getString(R.string.systemDefault),
                R.string.systemDefault)
            ThemeHelper.applyTheme(sharedPref.getThemeUI(resources.getString(R.string.systemDefault)))
        }
        binding.light.setOnClickListener {
            sharedPref.updateThemeUI(resources.getString(R.string.light), R.string.light)
            ThemeHelper.applyTheme(sharedPref.getThemeUI(resources.getString(R.string.light)))
        }
        binding.dark.setOnClickListener {
            sharedPref.updateThemeUI(resources.getString(R.string.dark), R.string.dark)
            ThemeHelper.applyTheme(sharedPref.getThemeUI(resources.getString(R.string.dark)))
        }

    }


    private fun refresh() {
        findNavController().navigate(R.id.action_settingsFragment_self)
        findNavController().popBackStack()
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