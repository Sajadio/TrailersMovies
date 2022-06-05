package com.example.trailers.ui.fragment.genres

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trailers.R
import com.example.trailers.databinding.FragmentGenresBinding
import com.example.trailers.ui.base.BaseFragment
import com.example.trailers.ui.fragment.genres.adapter.ChipsAdapter
import com.example.trailers.ui.fragment.genres.adapter.GenresPagingAdapter
import com.example.trailers.ui.fragment.home.adapter.OnClickListener
import com.example.trailers.ui.fragment.home.vm.HomeViewModel
import com.example.trailers.ui.fragment.search.adapter.PagingLoadStateAdapter
import com.example.trailers.utils.setAsActionBar
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.launch
import javax.inject.Inject

class GenresFragment : BaseFragment<FragmentGenresBinding>(R.layout.fragment_genres),
    OnClickListener {

    @Inject
    lateinit var vm: HomeViewModel
    private lateinit var adapter: GenresPagingAdapter
    private lateinit var adapterChips: ChipsAdapter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun initial() {
        binding.apply {
            titleToolbar.text = resources.getString(R.string.genres)
            (activity as AppCompatActivity?)?.setAsActionBar(toolbar, true)
            swiperefreshlayout.setOnRefreshListener {
                initialAdapter()
                swiperefreshlayout.isRefreshing = false
            }
            initialChipsAdapter()
        }
    }

    private fun initialChipsAdapter() {
        adapterChips = ChipsAdapter()
        vm.getGenresMovie.observe(this) { response ->
            response.data()?.let { data ->
                adapterChips.differ.submitList(data.genres)
                binding.rcChip.adapter = adapterChips
            }
        }
        adapterChips.onItemClickListener { onClickItem(it) }
    }

    private fun onClickItem(genreId: String?) {
        genreId?.let {
            vm.getGenresOfMovie(it)
            initialAdapter()
        }
    }

    private fun initialAdapter() {
        adapter = GenresPagingAdapter(this)
        binding.rcGenres.layoutManager = GridLayoutManager(context, 2)
        vm.getGenresOfMovie.observe(viewLifecycleOwner) { data ->
            lifecycleScope.launch {
                adapter.submitData(data)
            }
        }
        binding.rcGenres.hasFixedSize()
        binding.rcGenres.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PagingLoadStateAdapter { adapter.retry() },
            footer = PagingLoadStateAdapter { adapter.retry() }
        )
        adapter.addLoadStateListener { loadState ->
            val isEmptyList = loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
            showEmptyList(isEmptyList)

            // Only show the list if refresh succeeds
            binding.rcGenres.isVisible = loadState.source.refresh is LoadState.NotLoading
            // Show loading spinner during initial load or refresh
            (loadState.source.refresh is LoadState.Loading).also {
                binding.progressBar.isVisible = it
            }
        }
    }

    private fun showEmptyList(emptyList: Boolean) {
        binding.rcGenres.isVisible = !emptyList
    }

    override fun clickItem(id: Int?, navigation: Int?) {
        val bundle = Bundle()
        id?.let {
            bundle.putInt("id", it)
            findNavController().navigate(R.id.action_genresFragment_to_moiveFragment, bundle)
        }
    }

}