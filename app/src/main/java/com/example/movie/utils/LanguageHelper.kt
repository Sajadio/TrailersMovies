package com.example.movie.utils

import android.content.res.Configuration
import android.view.ContextThemeWrapper
import java.util.*


object LanguageHelper : ContextThemeWrapper() {
    var dLocale: Locale? = null

    init {
        updateConfig(this)
    }

    private fun updateConfig(wrapper: ContextThemeWrapper) {
        if (dLocale == Locale(""))
            return

        Locale.setDefault(dLocale)
        val configuration = Configuration()
        configuration.setLocale(dLocale)
        wrapper.applyOverrideConfiguration(configuration)
    }
}