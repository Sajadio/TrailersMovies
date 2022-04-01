package com.example.movie.utils

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.movie.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

fun AppCompatImageButton.favoriteItem(isFavorite: Boolean) {
    this.isVisible = isFavorite
    if (isFavorite)
        Toast.makeText(context, "Saved item", Toast.LENGTH_LONG).show()
    else
        Toast.makeText(context, "Deleted item", Toast.LENGTH_LONG).show()
}

@SuppressLint("ResourceAsColor")
fun ChipGroup.addChipView(chipText: String, layoutInflater: LayoutInflater,layout: Int) {
    val chip = layoutInflater.inflate(layout, this, false) as Chip
    chip.text = chipText
    chip.setOnClickListener {
        chip.setBackgroundColor(R.drawable.shadow_search_btn)
        Toast.makeText(context,"Clicked me",Toast.LENGTH_SHORT).show()
    }
    this.addView(chip)
}

fun ImageView.loadImage(url: Int) {
    Glide.with(this).load(url).into(this)
}

