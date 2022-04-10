package com.example.movie.ui.fragment.popular

import android.annotation.SuppressLint
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.R
import com.example.movie.data.repository.Repository
import com.example.movie.databinding.FragmentPopularBinding
import com.example.movie.ui.base.BaseFragment
import com.example.movie.ui.fragment.favorite.adapter.FavoriteAdapter
import com.example.movie.utils.setAsActionBar
import com.google.android.material.tabs.TabLayout

class PopularFragment : BaseFragment<FragmentPopularBinding>(R.layout.fragment_popular),
    TabLayout.OnTabSelectedListener {


    @SuppressLint("ResourceAsColor")
    override fun initial() {
        (activity as AppCompatActivity?)?.setAsActionBar(binding.toolbar, true)

        binding.rcPopular.adapter = FavoriteAdapter(Repository.getCategory())
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

}
