package com.sajjadio.trailers.ui.activity


import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.sajjadio.trailers.R
import com.sajjadio.trailers.databinding.ActivityMovieBinding
import com.sajjadio.trailers.ui.fragment.home.viewModel.StorageViewModel
import com.sajjadio.trailers.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    val viewModel: StorageViewModel by viewModels()

    private var _binding: ActivityMovieBinding? = null
    private val binding: ActivityMovieBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        observeUiPreferences()
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)

        binding.apply {
            NetworkHelper(context = this@HomeActivity).observe(this@HomeActivity) { state ->
                root.setSnackbar(state)
            }
        }
    }


    fun checkCurrentMode() =
        when (resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_UNDEFINED -> 0
            Configuration.UI_MODE_NIGHT_NO -> 1
            Configuration.UI_MODE_NIGHT_YES -> 2
            else -> -1
        }

    override fun onSupportNavigateUp(): Boolean {
        findNavController(R.id.nav_host_fragment).navigateUp()
        return true
    }

    private fun observeUiPreferences() {
        viewModel.selectedTheme.observe(this) { uiMode ->
            ThemeHelper.applyTheme(uiMode)
        }
    }

    override fun onStart() {
        super.onStart()

        binding.bottomnavigation.setupWithNavController(binding.navHostFragment.findNavController())
        binding.navHostFragment.findNavController()
            .addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.homeFragment, R.id.searchFragment, R.id.genresFragment -> {
                        binding.bottomnavigation.visibility = View.VISIBLE
                    }
                    else -> binding.bottomnavigation.visibility = View.GONE
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}