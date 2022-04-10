package com.example.movie.utils

import android.annotation.SuppressLint
import android.content.res.Configuration
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
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.bumptech.glide.Glide
import com.example.movie.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.util.*

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

fun ImageView.loadImage(url: Int) {
    Glide.with(this).load(url).into(this)
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
    layout: Int
) {
    chipText.forEach { type ->
        val chip = layoutInflater.inflate(layout, this, false) as Chip
        chip.text = type
        this.addView(chip)
    }
}

var isExpandLang = true
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

var isExpandTheme = true
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
