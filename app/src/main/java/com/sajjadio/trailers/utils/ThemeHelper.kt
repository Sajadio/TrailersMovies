package com.sajjadio.trailers.utils

import android.os.Build
import androidx.appcompat.app.AppCompatDelegate

object ThemeHelper {
    fun applyTheme(uiMode: String) {

        return when (uiMode) {
            Constant.LIGHT -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            Constant.DARK -> {
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