package com.example.movie.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.example.movie.R
import com.example.movie.databinding.ActivityMovieBinding
import java.lang.NullPointerException

class MovieActivity : AppCompatActivity() {

    private var _binding: ActivityMovieBinding? = null
    private val binding: ActivityMovieBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this,R.layout.activity_movie)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}