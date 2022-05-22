package com.example.trailers.ui.fragment.details.tv

import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.trailers.R
import com.example.trailers.databinding.FragmentTvBinding
import com.example.trailers.ui.base.BaseFragment
import com.example.trailers.utils.favoriteItem
import com.example.trailers.utils.listChips
import com.example.trailers.utils.setAsActionBar


class TVFragment : BaseFragment<FragmentTvBinding>(R.layout.fragment_tv) {

    override fun initial() {
        (activity as AppCompatActivity?)?.setAsActionBar(binding.toolbar,true)


//        binding.include.rvRelated.adapter = RelatedAdapter(HomeRepo.getCategory())
//        binding.include.rvActors.adapter = ActorsAdapter(HomeRepo.getCategory())

        binding.btnFavorite.setOnClickListener {
            binding.btnFavorite.favoriteItem(isFavorite = false)
            binding.btnNotFavorite.favoriteItem(isFavorite = true)
        }
        binding.btnNotFavorite.setOnClickListener {
            binding.btnFavorite.favoriteItem(isFavorite = true)
            binding.btnNotFavorite.favoriteItem(isFavorite = false)
        }


        var generes = ""
        listChips.forEach {
            binding.include.generes.text = "$generes •"
        }

//        binding.include.chipGroupEpisode.addChipWithTheme(
//            chipText = HomeRepo.getType().type,
//            layoutInflater = layoutInflater,
//            R.layout.layout_chips_with_theme
//        )

        binding.btnSheet.setOnClickListener {
            findNavController().navigate(R.id.action_tvFragment_to_optionsBottomSheetFragment)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        super.onCreateOptionsMenu(menu, inflater)
    }

}