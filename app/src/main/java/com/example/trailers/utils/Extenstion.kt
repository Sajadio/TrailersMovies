package com.example.trailers.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isVisible
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.trailers.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.snackbar.Snackbar
import okhttp3.Connection
import okhttp3.Response
import java.lang.Exception
import java.util.*


fun language() = if (Locale.getDefault().displayLanguage == "English") "en" else "ar"


@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.loadImage(url: String, imageSize: String?) {
    imageSize?.let {
        Glide.with(this)
        .load(Constant.IMAGE_PATH + imageSize + url)
        .error(R.drawable.error)
        .into(this)
    }
}

fun Activity.setAsActionBar(toolbar: Toolbar, isBack: Boolean = true, title: String? = null) {
    (this as AppCompatActivity).setSupportActionBar(toolbar)
    toolbar.title = title
    if (isBack) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }
}


private var stateActive = false
fun View.setSnackbar(state: Int) {
    if (stateActive) {
        Snackbar.make(this, resources.getString(state), Snackbar.LENGTH_LONG)
            .setBackgroundTint(resources.getColor(R.color.green)).show()
        stateActive = false
    }

    when (state) {
        R.string.noConnection -> {
            Snackbar.make(this, resources.getString(state), Snackbar.LENGTH_LONG).show()
            stateActive = true
        }
        R.string.notActive -> {
            Snackbar.make(this, resources.getString(state), Snackbar.LENGTH_LONG)
                .setBackgroundTint(resources.getColor(R.color.redLight)).show()
            stateActive = true
        }
    }

}

@SuppressLint("CommitPrefEdits")
fun Context.setThemes(theme: Int): Int {
    val pref = this.getSharedPreferences(Constant.PREFERENCE_NAME, Context.MODE_PRIVATE)
    val editor = pref.edit()
    editor.putInt(Constant.THEME_APP, theme)
    editor.apply()

    return pref.getInt(Constant.THEME_APP, 0)
}

fun View.visibility(isVisible: Boolean?) {
    this.isVisible = isVisible != true
}

fun Int.isConnection() = when (this) {
    R.string.connected -> true
    R.string.noConnection -> false
    else -> false
}

fun Window.hideSystemUI(view: View) {
    WindowCompat.setDecorFitsSystemWindows(this, false)
    WindowInsetsControllerCompat(this, view).let { controller ->
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}

fun NavDirections.movieToDestination(view: View?) {
    view?.findNavController()?.navigate(this)
}



