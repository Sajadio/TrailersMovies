package com.example.trailers.ui.activity


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.trailers.R
import com.example.trailers.databinding.ActivityMovieBinding
import com.example.trailers.ui.fragment.home.vm.StorageViewModel
import com.example.trailers.utils.*
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.layout_item_similar.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var vm: StorageViewModel
    private var _binding: ActivityMovieBinding? = null
    val binding: ActivityMovieBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        observeUiPreferences()
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)

        binding.apply {
            NetworkHelper(context = this@HomeActivity).observe(this@HomeActivity) { state ->
                root.setSnackbar(state)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        findNavController(R.id.nav_host_fragment).navigateUp()
        return true
    }

    private fun observeUiPreferences() {
        vm.selectedTheme.observe(this) { uiMode ->
            ThemeHelper.applyTheme(uiMode)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}