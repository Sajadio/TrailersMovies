package com.example.movie.ui.fragment.favorite

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.movie.R
import com.example.movie.databinding.FragmentFavoriteBinding
import com.example.movie.databinding.FragmentGenresBinding
import com.example.movie.ui.base.BaseFragment
import com.example.movie.utils.setAsActionBar

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(R.layout.fragment_favorite) {

    override fun initial() {
        (activity as AppCompatActivity?)?.setAsActionBar(binding.toolbar,true)
        binding.movie.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_favoriteFragment_to_moiveFragment)
        }

        binding.tv.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_favoriteFragment_to_tvFragment)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_app_bar,menu)
        menu.findItem(R.id.search).isVisible = false
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24)
        binding.toolbar.setNavigationOnClickListener { activity!!.onBackPressed() }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete) {
            Toast.makeText(context,"Deleted me",Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }
}