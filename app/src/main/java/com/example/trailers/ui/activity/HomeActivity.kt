package com.example.trailers.ui.activity


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.trailers.R
import com.example.trailers.databinding.ActivityMovieBinding
import com.example.trailers.ui.fragment.home.vm.StorageViewModel
import com.example.trailers.utils.NetworkHelper
import com.example.trailers.utils.ThemeHelper
import com.example.trailers.utils.setSnackbar
import dagger.android.AndroidInjection
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