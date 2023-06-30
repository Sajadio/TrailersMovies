package com.sajjadio.trailers.ui.details

import android.annotation.SuppressLint
import android.view.ViewGroup
import com.sajjadio.trailers.BR
import com.sajjadio.trailers.R
import com.sajjadio.trailers.domain.model.Cast
import com.sajjadio.trailers.domain.model.MovieDetails
import com.sajjadio.trailers.domain.model.CommonResult
import com.sajjadio.trailers.domain.model.Poster
import com.sajjadio.trailers.ui.base.BaseAdapter
import com.sajjadio.trailers.ui.base.BaseInteractListener
import com.sajjadio.trailers.ui.details.adapter.ActorsAdapter
import com.sajjadio.trailers.ui.details.adapter.GalleryAdapter
import com.sajjadio.trailers.ui.details.adapter.SimilarAdapter
import com.sajjadio.trailers.ui.details.utils.DetailsItem

class DetailsAdapter(
    val listener: DetailsInteractListener
) : BaseAdapter<DetailsItem>(listOf(), listener) {

    override var layoutId: Int = 0

    @SuppressLint("NotifyDataSetChanged")
    fun addNestedItem(newItem: List<DetailsItem>) {
        setItems(newItem.sortedBy { it.rank })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        layoutId = getLayoutId(viewType)
        return super.onCreateViewHolder(parent, viewType)
    }

    private fun getLayoutId(viewType: Int): Int {
        return when (viewType) {
            LAYOUT_MOVIE -> LAYOUT_MOVIE
            LAYOUT_GALLERY -> LAYOUT_GALLERY
            LAYOUT_ACTORS -> LAYOUT_ACTORS
            LAYOUT_SIMILAR -> LAYOUT_SIMILAR
            else -> throw Exception("unknown view type")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (val currentItem = getItems()[position]) {
            is DetailsItem.MovieItem -> bindMovieItem(
                holder as ItemViewHolder,
                currentItem.movieDetails
            )
            is DetailsItem.GalleryItem -> bindGalleryItem(holder as ItemViewHolder, currentItem.poster)
            is DetailsItem.ActorItem -> bindActorItem(holder as ItemViewHolder, currentItem.actors)
            is DetailsItem.SimilarItem -> bindSimilarItem(
                holder as ItemViewHolder,
                currentItem.commonResult
            )
        }
    }

    private fun bindMovieItem(holder: ItemViewHolder, item: MovieDetails) {
        holder.binding.setVariable(BR.item, item)
    }

    private fun bindGalleryItem(holder: ItemViewHolder, items: List<Poster>) {
        holder.binding.apply {
            setVariable(BR.adapter, GalleryAdapter(items, listener))
            setVariable(BR.listener, listener)
        }
    }


    private fun bindActorItem(holder: ItemViewHolder, items: List<Cast>) {
        holder.binding.apply {
            setVariable(BR.adapter, ActorsAdapter(items, listener))
            setVariable(BR.listener, listener)
        }
    }

    private fun bindSimilarItem(holder: ItemViewHolder, items: List<CommonResult>) {
        holder.binding.apply {
            setVariable(BR.adapter, SimilarAdapter(items, listener))
            setVariable(BR.listener, listener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItems()[position]) {
            is DetailsItem.MovieItem -> LAYOUT_MOVIE
            is DetailsItem.GalleryItem -> LAYOUT_GALLERY
            is DetailsItem.ActorItem -> LAYOUT_ACTORS
            is DetailsItem.SimilarItem -> LAYOUT_SIMILAR
        }
    }

    private companion object {
        const val LAYOUT_MOVIE = R.layout.layout_details_item
        const val LAYOUT_GALLERY = R.layout.layout_gallery_recycler
        const val LAYOUT_ACTORS = R.layout.layout_actor_recycler
        const val LAYOUT_SIMILAR = R.layout.layout_similar_recycler
    }
}

interface DetailsInteractListener : BaseInteractListener {
    fun onClickGalleryItem()
    fun onClickSeeAllGallery()
    fun onClickSeeAllActors()
    fun onClickActorItem(id: Int)
    fun onClickSeeAllSimilar()
}