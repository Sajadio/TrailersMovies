package com.example.movie.utils

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import com.example.movie.R

object ThemeHelper {
    private const val LIGHT_MODE = R.string.light
    private const val DARK_MODE = R.string.dark

    fun applyTheme(theme: Int) {

        when (theme) {
            LIGHT_MODE -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            DARK_MODE -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            else -> {
                checkSystemVersion()
            }
        }
    }

    private fun checkSystemVersion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
        }
    }
}