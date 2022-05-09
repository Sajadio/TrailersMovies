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
import com.example.movie.databinding.FragmentPopularBinding
import com.example.movie.ui.base.BaseFragment
import com.example.movie.ui.fragment.home.HomeFragment
import com.example.movie.ui.fragment.search.adapter.SearchAdapter
import com.example.movie.ui.fragment.search.vm.SearchViewModel
import com.example.movie.ui.vm.MDBViewModel
import com.example.movie.utils.setAsActionBar
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search),
    androidx.appcompat.widget.SearchView.OnQueryTextListener {

    @Inject
    lateinit var vm: SearchViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun initial() {
        (activity as AppCompatActivity?)?.setAsActionBar(binding.toolbar, true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_app_bar, menu)

        menu.findItem(R.id.delete).isVisible = false
        menu.findItem(R.id.setting).isVisible = false
        menu.findItem(R.id.movie).isVisible = false
        menu.findItem(R.id.tv).isVisible = false

        val search = menu.findItem(R.id.search)
        var searchView = search?.actionView as? androidx.appcompat.widget.SearchView
        searchView?.queryHint = "Search here.."
        searchView?.setOnQueryTextListener(this)
        searchView?.maxWidth = Integer.MAX_VALUE

        super.onCreateOptionsMenu(menu, inflater)

    }


    override fun onQueryTextSubmit(query: String?) = false

    override fun onQueryTextChange(query: String?): Boolean {
        query?.let { vm.getMovieSearch(it) }
        vm.getMoviesSearch.observe(this) {
            val adapter = SearchAdapter(it.results)
            binding.rcSearch.adapter = adapter
        }
        return true
    }

}