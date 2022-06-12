package com.example.trailers.ui.fragment.movie

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trailers.R
import com.example.trailers.data.model.movie.actorsmovie.ActorsMovie
import com.example.trailers.databinding.FragmentActorsBinding
import com.example.trailers.ui.base.BaseFragment
import com.example.trailers.ui.fragment.movie.adapter.ActorsMovieAdapter
import com.example.trailers.ui.fragment.movie.vm.MovieViewModel
import com.example.trailers.utils.NetworkStatus
import com.example.trailers.utils.setAsActionBar
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.launch
import javax.inject.Inject


class ActorsFragment : BaseFragment<FragmentActorsBinding>(R.layout.fragment_actors) {

    @Inject
    lateinit var vm: MovieViewModel
    private val args: ActorsFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun initial() {
        (activity as AppCompatActivity?)?.setAsActionBar(binding.toolbar, true)
        binding.apply {
            titleToolbar.text = args.info.original_name
            swiperefreshlayout.setOnRefreshListener {
                initialAdapter()
                swiperefreshlayout.isRefreshing = false
            }
            initialAdapter()

        }

    }

    private fun initialAdapter() {
        lifecycleScope.launch {
            vm.getMovieOfActor(args.info.id).observe(this@ActorsFragment) {
                stateManagement(it)
                it.data()?.cast?.let { cast ->
                    val adapter = ActorsMovieAdapter(cast)
                    binding.rvActors.layoutManager = GridLayoutManager(context, 3)
                    binding.rvActors.adapter = adapter
                    adapter.onItemClickListener { id ->
                        val bundle = Bundle()
                        id?.let {
                            bundle.putInt("id", it)
                            findNavController()
                                .navigate(
                                    R.id.action_actorsFragment_to_moiveFragment,
                                    bundle)
                        }
                    }
                }
            }
        }
    }

    private fun stateManagement(networkStatus: NetworkStatus<ActorsMovie>) {
        binding.progressBar.isVisible = (networkStatus is NetworkStatus.Loading)
    }
}