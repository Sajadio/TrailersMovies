package com.sajjadio.trailers.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.sajjadio.trailers.R
import com.sajjadio.trailers.databinding.ActivityMovieBinding
import com.sajjadio.trailers.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private var _binding: ActivityMovieBinding? = null
    private val binding: ActivityMovieBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)
        checkInternetConnection()
    }

    private fun checkInternetConnection() {
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


    override fun onStart() {
        super.onStart()
        binding.bottomNavigation.setupWithNavController(binding.navHostFragment.findNavController())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}