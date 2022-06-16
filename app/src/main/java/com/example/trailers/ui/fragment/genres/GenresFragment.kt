package com.example.trailers.ui.fragment.genres

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trailers.R
import com.example.trailers.databinding.FragmentGenresBinding
import com.example.trailers.ui.base.BaseFragment
import com.example.trailers.ui.fragment.common.CommonFragmentDirections
import com.example.trailers.ui.fragment.genres.adapter.ChipsAdapter
import com.example.trailers.ui.fragment.genres.adapter.GenresPagingAdapter
import com.example.trailers.ui.fragment.genres.vm.GenresViewModel
import com.example.trailers.ui.fragment.search.adapter.PagingLoadStateAdapter
import com.example.trailers.utils.*
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class GenresFragment : BaseFragment<FragmentGenresBinding>(R.layout.fragment_genres) {

    @Inject
    lateinit var vm: GenresViewModel
    private lateinit var adapter: GenresPagingAdapter
    private lateinit var adapterChips: ChipsAdapter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            activity?.setAsActionBar(toolbar = toolbar,
                isBack = true,
                title = resources.getString(R.string.genres))

            checkConnection()
            refresh()
            initialChipsAdapter()
        }
    }

    private fun checkConnection() {
        NetworkHelper(context = requireContext()).observe(viewLifecycleOwner) { state ->
            vm.checkConnection(state.isConnection())
        }
    }

    private fun refresh() {
        binding.swiperefreshlayout.setOnRefreshListener {
            checkConnection()
            initialAdapter()
            binding.swiperefreshlayout.isRefreshing = false
        }
    }

    private fun initialChipsAdapter() {
        vm.getGenresMovie.observe(viewLifecycleOwner) { data ->
            adapterChips = ChipsAdapter(data = data)
            binding.rcChip.adapter = adapterChips
            adapterChips.onItemClickListener { onClickItem(it) }
        }
        binding.rcChip.hasFixedSize()
    }

    private fun onClickItem(genreId: String?) {
        genreId?.let {
            vm.getGenresOfMovie(it)
            initialAdapter()
        }
    }

    private fun initialAdapter() {
        adapter = GenresPagingAdapter()
        binding.rcGenres.layoutManager = GridLayoutManager(context, 2)
        vm.getGenresOfMovie.observe(viewLifecycleOwner) { data ->
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
            val isEmptyList = loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
            showEmptyList(!isEmptyList)

            binding.rcGenres.isVisible = loadState.source.refresh is LoadState.NotLoading
            (loadState.source.refresh is LoadState.Loading).also {
                binding.progressBar.isVisible = it
            }
        }
    }

    private fun showEmptyList(emptyList: Boolean) {
        binding.rcGenres.isVisible = emptyList
    }

}