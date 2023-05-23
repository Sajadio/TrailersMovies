package com.sajjadio.trailers.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.sajjadio.trailers.R
import com.sajjadio.trailers.data.model.movie.actors.Actors
import com.sajjadio.trailers.data.model.movie.actors.Cast
import com.sajjadio.trailers.data.model.movie.id.IDMovie
import com.sajjadio.trailers.data.model.movie.similar.Similar
import com.sajjadio.trailers.databinding.FragmentDetailsBinding
import com.sajjadio.trailers.ui.base.BaseFragment
import com.sajjadio.trailers.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.layout_item_similar.root


@AndroidEntryPoint
class DetailsFragment :
    BaseFragment<FragmentDetailsBinding, DetailsViewModel>(R.layout.fragment_details) {

    override val LOG_TAG = this::class.java.simpleName
    override val viewModelClass = DetailsViewModel::class.java
    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getID(args.id)
        setGenres()
        setupDetailsRecyclerView()
    }

    private fun setupDetailsRecyclerView() {
        binding.recyclerViewDetails.adapter = DetailsAdapter(viewModel).apply {
            viewModel.responseDetailsData.observe(viewLifecycleOwner) {
                it.data()?.let { it1 -> addNestedItem(it1) }
            }
        }
    }

    private fun setPlayVideo() {
        viewModel.playVideo.observe(viewLifecycleOwner) {
            it?.data()?.results?.map {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(Constant.YOUTUBE_BASE + it.key)
                    )
                )
            }
        }
    }


    private fun getMovieOfActor(cast: Cast) {
//        val action = DetailsFragmentDirections.actionMoiveFragmentToActorsFragment(cast)
//        action.movieToDestination(view = this.root)
    }

    private fun setGenres() {
        viewModel.responseData.observe(viewLifecycleOwner) { state ->
            val genres = state?.data()?.genres
            var genre = ""
            if (genres != null) {
                for (i in genres) {
                    genre += if (i != genres.last())
                        "${i.name} | "
                    else
                        i.name
                }
            }
        }
    }
}
