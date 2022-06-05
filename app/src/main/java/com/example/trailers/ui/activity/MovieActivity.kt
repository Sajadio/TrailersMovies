package com.example.trailers.ui.activity


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.trailers.R
import com.example.trailers.databinding.ActivityMovieBinding
import com.example.trailers.ui.fragment.home.vm.HomeViewModel
import com.example.trailers.utils.NetworkHelper
import com.example.trailers.utils.setSnackbar
import dagger.android.AndroidInjection
import javax.inject.Inject

class MovieActivity : AppCompatActivity() {

    @Inject
    lateinit var vm: HomeViewModel

    private var _binding: ActivityMovieBinding? = null
    val binding: ActivityMovieBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)
        binding.apply {
            NetworkHelper(context = this@MovieActivity).observe(this@MovieActivity) { state ->
                root.setSnackbar(state)
                connection.isVisible = vm.checkConnection(state)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.navigation.setupWithNavController(findNavController(R.id.nav_host_fragment))
    }

    override fun onSupportNavigateUp(): Boolean {
        findNavController(R.id.nav_host_fragment).navigateUp()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}