package com.example.trailers.ui.fragment.coming

import android.annotation.SuppressLint
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.trailers.R
import com.example.trailers.data.model.trend.Trending
import com.example.trailers.databinding.FragmentPopularBinding
import com.example.trailers.ui.base.BaseFragment
import com.example.trailers.ui.base.adapter.BaseOnClickItem
import com.example.trailers.utils.setAsActionBar
import com.google.android.material.tabs.TabLayout

class UpComingFragment : BaseFragment<FragmentPopularBinding>(R.layout.fragment_popular),
    TabLayout.OnTabSelectedListener , BaseOnClickItem<Trending> {


    @SuppressLint("ResourceAsColor")
    override fun initial() {
        (activity as AppCompatActivity?)?.setAsActionBar(binding.toolbar, true)

//        val adapter = PopularAdapter(HomeRepo.getCategory(),this)
//        binding.rcPopular.adapter = adapter

//        binding.rcPopular.adapter = FavoriteAdapter(HomeRepo.getCategory())
        if (binding.tabLayout.getTabAt(0)?.isSelected == true) {
            Log.d("sajjadio", "nnnnnnnn")

        }
        binding.tabLayout.addOnTabSelectedListener(this)
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        when (tab?.position) {
            0 -> {
                Log.d("sajjadio", "${tab.text}")
            }

            1 -> {
                Log.d("sajjadio", "${tab.text}")
            }
            2 -> {
                Log.d("sajjadio", "${tab.text}")
            }
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        //TODO("Not yet implemented")
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        // TODO("Not yet implemented")
    }

    override fun clickedItem(item: Trending) {
        // TODO("Not yet implemented")
    }

}
