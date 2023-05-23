package com.sajjadio.trailers.ui.fragment.genres

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sajjadio.trailers.R
import com.sajjadio.trailers.databinding.FragmentGenresBinding
import com.sajjadio.trailers.ui.base.BaseFragment
import com.sajjadio.trailers.ui.fragment.genres.adapter.GenresPagingAdapter
import com.sajjadio.trailers.ui.fragment.genres.viewModel.GenresViewModel
import com.sajjadio.trailers.ui.fragment.search.adapter.PagingLoadStateAdapter
import com.sajjadio.trailers.utils.*
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_genres.*
import kotlinx.coroutines.launch


@AndroidEntryPoint
class GenresFragment : BaseFragment<FragmentGenresBinding>(R.layout.fragment_genres) {


    private val viewModel: GenresViewModel by viewModels()

    private lateinit var adapter: GenresPagingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialAdapter()
        checkConnection()
        refresh()
        setUpTabLayout()
    }


    private fun checkConnection() {
        NetworkHelper(context = requireContext()).observe(viewLifecycleOwner) { state ->

        }
    }

    private fun refresh() {
        binding.swiperefreshlayout.setOnRefreshListener {
            checkConnection()
            initialAdapter()
            binding.swiperefreshlayout.isRefreshing = false
        }
    }

    private fun setUpTabLayout() {

        viewModel.listGenresMovie.observe(viewLifecycleOwner) { data ->
            data.reversed().forEach {
                binding.tabLayout.addTab(binding.tabLayout.newTab().setText(it.name.toString()))
            }
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewModel.saveCurrentPosition.postValue(tab.position)
                viewModel.getID(tab.text.toString())
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        // to save current position when click back btn press
        viewModel.saveCurrentPosition.observe(viewLifecycleOwner) { position ->
            binding.tabLayout.getTabAt(position)?.select()
        }
    }


    private fun initialAdapter() {
        adapter = GenresPagingAdapter()

        val gridLayoutManager = GridLayoutManager(context, 2)
        binding.rcGenres.layoutManager = gridLayoutManager
        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL


        viewModel.responseListOfMovie.observe(viewLifecycleOwner) { data ->
            lifecycleScope.launch {
                adapter.submitData(data)
            }
        }

        adapter.onItemClickListener { id ->
            id?.let {
                val action = GenresFragmentDirections.actionGenresFragmentToMoiveFragment(id)
                action.movieToDestination(view)
            }
        }

        binding.rcGenres.hasFixedSize()
        loadStateAdapter()
    }

    private fun loadStateAdapter() {
        binding.rcGenres.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PagingLoadStateAdapter { adapter.retry() },
            footer = PagingLoadStateAdapter { adapter.retry() }
        )

        adapter.addLoadStateListener { loadState ->
            val isEmptyList =
                loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
            showEmptyList(!isEmptyList)

            binding.rcGenres.isVisible = loadState.source.refresh is LoadState.NotLoading
            (loadState.source.refresh is LoadState.Loading).also {
                stateManagement(it)
            }
        }
    }

    private fun showEmptyList(emptyList: Boolean) {
        binding.rcGenres.isVisible = emptyList
    }


    private fun stateManagement(state: Boolean) {
        if (state)
            binding.shimmer.startShimmer()
        else
            binding.shimmer.stopShimmer()

        binding.shimmer.isVisible = state
        binding.swiperefreshlayout.isVisible = !state

    }

}