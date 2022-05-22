package com.example.trailers.ui.activity


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.example.trailers.R
import com.example.trailers.databinding.ActivityMovieBinding
import com.example.trailers.ui.fragment.home.vm.HomeViewModel
import com.example.trailers.utils.NetworkHelper
import com.example.trailers.utils.setSnackbar
import javax.inject.Inject

class MovieActivity : AppCompatActivity() {

    private var _binding: ActivityMovieBinding? = null
    private val binding: ActivityMovieBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}