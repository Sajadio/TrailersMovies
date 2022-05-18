package com.example.movie.ui.fragment.genres

import androidx.appcompat.app.AppCompatActivity
import com.example.movie.R
import com.example.movie.data.model.trend.Trending
import com.example.movie.databinding.FragmentGenresBinding
import com.example.movie.ui.base.BaseFragment
import com.example.movie.ui.base.adapter.BaseOnClickItem
import com.example.movie.utils.setAsActionBar
import com.skydoves.transformationlayout.onTransformationStartContainer

class GenresFragment : BaseFragment<FragmentGenresBinding>(R.layout.fragment_genres) ,BaseOnClickItem<Trending>{

    override fun initial() {
        onTransformationStartContainer()

        binding.titleToolbar.text = "Generes"
        (activity as AppCompatActivity?)?.setAsActionBar(binding.toolbar, true)

//        binding.rcGeneres.adapter = GenresAdapter(MDBRepo.getCategory(),this)
//
//        binding.chipGroupGenres.addChipWithTheme(
//            chipText = MDBRepo.getType().type,
//            layoutInflater = layoutInflater,
//            R.layout.layout_chips_with_theme
//        )

    }

    override fun clickedItem(item: Trending) {

    }

}