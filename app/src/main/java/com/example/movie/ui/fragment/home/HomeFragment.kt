package com.example.movie.ui.fragment.home

import android.widget.Toast
import com.example.movie.R
import com.example.movie.databinding.FragmentHomeBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.movie.data.m.Genres
import com.example.movie.data.m.Trend
import com.example.movie.data.repository.Repository
import com.example.movie.ui.fragment.home.adapter.HomeAdapter
import com.example.movie.ui.fragment.home.adapter.OnClickListener
import android.widget.ImageView
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.movie.ui.base.BaseFragment
import com.example.movie.utils.setAsActionBar
import kotlinx.android.synthetic.main.layout_item_actors.*


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home), OnClickListener {

    override fun initial() {
        (activity as AppCompatActivity?)?.setAsActionBar(binding.toolbar, false)

        filterResult(resources.getString(R.string.movie))

        binding.btnSearch.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

        binding.btnMenu.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_homeFragment_to_bottomSheetHome)
        }

        val adapter = HomeAdapter(Repository.getAllItems(), lifecycle, this)
        binding.parentRecyclerView.adapter = adapter
        binding.parentRecyclerView.setHasFixedSize(true)

    }



    private fun filterResult(string: String) {
        binding.titleToolbar.text = string
    }


    override fun trendItem(trend: Trend) {
        Toast.makeText(requireContext(), "trend", Toast.LENGTH_SHORT).show()
    }

    override fun category(category: ImageView) {
        findNavController().navigate(R.id.action_homeFragment_to_moiveFragment)
    }

    override fun openItem(category: Genres) {
        Toast.makeText(requireContext(), "view more category", Toast.LENGTH_SHORT).show()
    }

    override fun popular(popular: Int) {
        findNavController().navigate(R.id.action_homeFragment_to_popularFragment)
    }

}