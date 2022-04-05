package com.example.movie.ui.fragment.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.movie.R
import com.example.movie.databinding.FragmentHomeBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.movie.data.m.Genres
import com.example.movie.data.m.Trend
import com.example.movie.data.repository.Repository
import com.example.movie.ui.fragment.home.adapter.HomeAdapter
import com.example.movie.ui.fragment.home.adapter.OnClickListener
import android.widget.ArrayAdapter
import com.example.movie.ui.base.BaseFragment
import com.example.movie.utils.setAsActionBar


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home), OnClickListener {

    override fun initial() {
        (activity as AppCompatActivity?)?.setAsActionBar(binding.toolbar,false)

        filterResult(resources.getString(R.string.movie))

        binding.btnSearch.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

        val adapter = HomeAdapter(Repository.getAllItems(), lifecycle, this)
        binding.parentRecyclerView.adapter = adapter
        binding.parentRecyclerView.setHasFixedSize(true)

        binding.btnMenu.setOnClickListener {
            view?.let { it1 -> showDialog(it1) }
        }

    }

    private fun showDialog(view: View) {

        val builderSingle = AlertDialog.Builder(requireContext())
        val arrayAdapter =
            ArrayAdapter<String>(requireContext(), android.R.layout.select_dialog_singlechoice)
        arrayAdapter.add(resources.getString(R.string.settings))
        arrayAdapter.add(resources.getString(R.string.movie))
        arrayAdapter.add(resources.getString(R.string.tv))

        builderSingle.setAdapter(arrayAdapter) { _, which ->
            when (arrayAdapter.getItem(which)) {
                resources.getString(R.string.settings) -> {
                    view.findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
                }
                resources.getString(R.string.movie) -> {
                    view.findNavController().navigate(R.id.action_homeFragment_to_moiveFragment)
                }
                resources.getString(R.string.tv) -> {
                    view.findNavController().navigate(R.id.action_homeFragment_to_tvFragment)
                }
            }
        }
        builderSingle.show()
    }

    private fun filterResult(string: String) {
        binding.titleToolbar.text = string
    }


    override fun trendItem(trend: Trend) {
        Toast.makeText(requireContext(), "trend", Toast.LENGTH_SHORT).show()
    }

    override fun category(category: Int) {
        Toast.makeText(requireContext(), "categoryMove", Toast.LENGTH_SHORT).show()
    }

    override fun openItem(category: Genres) {
        Toast.makeText(requireContext(), "view more category", Toast.LENGTH_SHORT).show()
    }

    override fun popular(popular: Int) {
        Toast.makeText(requireContext(), "popularMove", Toast.LENGTH_SHORT).show()
    }

}