package com.example.trailers.ui.fragment.genres

import androidx.appcompat.app.AppCompatActivity
import com.example.trailers.R
import com.example.trailers.data.model.trend.Trending
import com.example.trailers.databinding.FragmentGenresBinding
import com.example.trailers.ui.base.BaseFragment
import com.example.trailers.ui.base.adapter.BaseOnClickItem
import com.example.trailers.utils.setAsActionBar
import com.skydoves.transformationlayout.onTransformationStartContainer

class GenresFragment : BaseFragment<FragmentGenresBinding>(R.layout.fragment_genres) ,
    BaseOnClickItem<Trending> {

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