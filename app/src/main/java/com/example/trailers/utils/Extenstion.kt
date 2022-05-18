package com.example.trailers.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.trailers.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.snackbar.Snackbar


fun AppCompatImageButton.favoriteItem(isFavorite: Boolean) {
    this.isVisible = isFavorite
    if (isFavorite)
        Toast.makeText(context, "Saved item", Toast.LENGTH_LONG).show()
    else
        Toast.makeText(context, "Deleted item", Toast.LENGTH_LONG).show()
}

@SuppressLint("ResourceAsColor")
fun ChipGroup.addChipView(chipText: String, layoutInflater: LayoutInflater, layout: Int) {
    val chip = layoutInflater.inflate(layout, this, false) as Chip
    chip.text = chipText
    chip.setOnClickListener {
        chip.setBackgroundColor(R.drawable.shadow_search_btn)
        Toast.makeText(context, "Clicked me", Toast.LENGTH_SHORT).show()
    }
    this.addView(chip)
}

fun ImageView.loadImagetesting(url: Int) {
    Glide.with(this).load(url).into(this)
}

fun ImageView.loadImage(url: String) {
    Glide.with(this).load(Constant.IMAGE_PATH + url).into(this)
}

fun AppCompatActivity.setAsActionBar(toolbar: Toolbar, isBack: Boolean) {
    toolbar.title = ""
    toolbar.subtitle = ""
    setSupportActionBar(toolbar)
    if (isBack) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }
}

@SuppressLint("ResourceAsColor")
fun ChipGroup.addChipWithTheme(
    chipText: List<String>,
    layoutInflater: LayoutInflater,
    layout: Int,
) {
    chipText.forEach { type ->
        val chip = layoutInflater.inflate(layout, this, false) as Chip
        chip.text = type
        this.addView(chip)
    }
}

private var isExpandLang = true
fun View.expandLang() {
    if (isExpandLang) {
        this.startCustomAnimation(R.anim.fade_in, 500L)
        this.isVisible = isExpandLang
        isExpandLang = false
    } else {
        this.isVisible = isExpandLang
        isExpandLang = true
    }
}

private var isExpandTheme = true
fun View.expandTheme() {
    if (isExpandTheme) {
        this.startCustomAnimation(R.anim.fade_in, 500L)
        this.isVisible = isExpandTheme
        isExpandTheme = false
    } else {
        this.isVisible = isExpandTheme
        isExpandTheme = true
    }
}

fun View.startCustomAnimation(anim: Int, duration: Long) {
    val setAnim: Animation = AnimationUtils.loadAnimation(this.context, anim)
    setAnim.duration = duration
    this.startAnimation(setAnim)
}

fun View.endCustomAnimation(anim: Int) {
    val setAnim: Animation = AnimationUtils.loadAnimation(this.context, anim)
    this.startAnimation(setAnim)
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

