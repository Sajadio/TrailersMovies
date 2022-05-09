package com.example.movie.utils

import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import com.example.movie.R

object ThemeHelper {
    fun applyTheme(theme: Int?) {

        return when (theme) {
            R.string.light -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            R.string.dark -> {
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