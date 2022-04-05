package com.example.movie.ui.fragment.details.tv

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.R
import com.example.movie.databinding.FragmentTvBinding
import com.example.movie.ui.base.BaseFragment
import com.example.movie.utils.favoriteItem
import com.example.movie.utils.listChips
import com.example.movie.utils.setAsActionBar
import com.google.android.material.chip.Chip


class TVFragment : BaseFragment<FragmentTvBinding>(R.layout.fragment_tv) {

    override fun initial() {
        (activity as AppCompatActivity?)?.setAsActionBar(binding.toolbar,true)

        binding.btnFavorite.setOnClickListener {
            binding.btnFavorite.favoriteItem(isFavorite = false)
            binding.btnNotFavorite.favoriteItem(isFavorite = true)
        }
        binding.btnNotFavorite.setOnClickListener {
            binding.btnFavorite.favoriteItem(isFavorite = true)
            binding.btnNotFavorite.favoriteItem(isFavorite = false)
        }


        addChipEpisodeView(listChips)

        binding.btnSheet.setOnClickListener {
            (activity)?.supportFragmentManager.let {
                OptionsBottomSheetFragment.newInstance(Bundle()).apply {
                    it?.let { it1 -> show(it1, tag) }
                }
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun addChipEpisodeView(chipText: MutableList<String>) {
        val chipGroup = binding.include.chipGroupEpisode
        chipText.forEach { type ->
            val chip =
                layoutInflater.inflate(R.layout.layout_chips_eposidoe, chipGroup, false) as Chip
            chip.text = type
            chipGroup.addView(chip)
        }
    }

}