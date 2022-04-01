package com.example.movie.ui.fragment.search

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.movie.R
import com.example.movie.databinding.FragmentSearchBinding
import android.app.SearchManager
import android.content.Context

import android.view.MenuInflater
import androidx.core.content.ContextCompat
import androidx.core.view.MenuItemCompat

import androidx.core.content.ContextCompat.getSystemService





class SearchFragment : Fragment(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        binding.toolbar.title = ""
        binding.toolbar.subtitle = ""
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_app_bar,menu)
        menu.findItem(R.id.delete).isVisible = false
        val search = menu.findItem(R.id.search)
        var searchView = search?.actionView as? androidx.appcompat.widget.SearchView
        searchView?.queryHint = "Search here.."
        searchView?.setOnQueryTextListener(this)
        searchView?.maxWidth = Integer.MAX_VALUE

        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24)
        binding.toolbar.setNavigationOnClickListener { activity!!.onBackPressed() }

        super.onCreateOptionsMenu(menu, inflater)

    }



    override fun onQueryTextSubmit(query: String?) = false

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.d("sajjadio", "onQueryTextChange: $newText")
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // for prevent memory leaks
        _binding = null
    }


}