package com.example.movie.ui.fragment.genres

import android.graphics.Color
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.R
import com.example.movie.data.m.Type
import com.example.movie.data.repository.Repository
import com.example.movie.databinding.FragmentGenresBinding
import com.example.movie.ui.base.BaseFragment
import com.example.movie.ui.fragment.genres.adapter.GenresAdapter
import com.example.movie.utils.addChipWithTheme
import com.example.movie.utils.setAsActionBar
import com.google.android.material.chip.Chip

class GenresFragment : BaseFragment<FragmentGenresBinding>(R.layout.fragment_genres) {

    override fun initial() {
        binding.titleToolbar.text = "Generes"
        (activity as AppCompatActivity?)?.setAsActionBar(binding.toolbar, true)

        binding.rcGeneres.adapter = GenresAdapter(Repository.getCategory())

        binding.chipGroupGenres.addChipWithTheme(
            chipText = Repository.getType().type,
            layoutInflater = layoutInflater,
            R.layout.layout_chips_with_theme
        )

    }

}