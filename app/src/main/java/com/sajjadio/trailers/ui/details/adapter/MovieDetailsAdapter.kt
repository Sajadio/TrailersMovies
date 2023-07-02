package com.sajjadio.trailers.ui.details.adapter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.ViewGroup
import com.sajjadio.trailers.BR
import com.sajjadio.trailers.R
import com.sajjadio.trailers.domain.model.Cast
import com.sajjadio.trailers.domain.model.MovieDetails
import com.sajjadio.trailers.domain.model.CommonResult
import com.sajjadio.trailers.domain.model.Image
import com.sajjadio.trailers.ui.base.BaseAdapter
import com.sajjadio.trailers.ui.base.BaseInteractListener
import com.sajjadio.trailers.ui.details.utils.MovieDetailsItem

class MovieDetailsAdapter(
    val listener: MovieDetailsInteractListener
) : BaseAdapter<MovieDetailsItem>(listOf(), listener) {

    override var layoutId: Int = 0

    @SuppressLint("NotifyDataSetChanged")
    fun addNestedItem(newItem: List<MovieDetailsItem>) {
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
            LAYOUT_PERSON -> LAYOUT_PERSON
            LAYOUT_SIMILAR -> LAYOUT_SIMILAR
            else -> throw Exception("unknown view type")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (val currentItem = getItems()[position]) {
            is MovieDetailsItem.MovieItem -> bindMovieItem(
                holder as ItemViewHolder,
                currentItem.movieDetails
            )
            is MovieDetailsItem.GalleryItem -> bindGalleryItem(holder as ItemViewHolder, currentItem.image)
            is MovieDetailsItem.PersonOfMovieItem -> bindPersonItem(holder as ItemViewHolder, currentItem.person)
            is MovieDetailsItem.SimilarItemMovie -> bindSimilarItem(
                holder as ItemViewHolder,
                currentItem.commonResult
            )
        }
    }

    private fun bindMovieItem(holder: ItemViewHolder, item: MovieDetails) {
        holder.binding.setVariable(BR.item, item)
    }

    private fun bindGalleryItem(holder: ItemViewHolder, items: List<Image>) {
        holder.binding.apply {
            setVariable(BR.adapter, GalleryOdMovieAdapter(items, listener))
            setVariable(BR.listener, listener)
        }
    }


    private fun bindPersonItem(holder: ItemViewHolder, items: List<Cast>) {
        holder.binding.apply {
            setVariable(BR.adapter, PersonOfMovieAdapter(items, listener))
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
            is MovieDetailsItem.MovieItem -> LAYOUT_MOVIE
            is MovieDetailsItem.GalleryItem -> LAYOUT_GALLERY
            is MovieDetailsItem.PersonOfMovieItem -> LAYOUT_PERSON
            is MovieDetailsItem.SimilarItemMovie -> LAYOUT_SIMILAR
        }
    }

    private companion object {
        const val LAYOUT_MOVIE = R.layout.layout_movie_details_item
        const val LAYOUT_GALLERY = R.layout.layout_gallery_recycler
        const val LAYOUT_PERSON = R.layout.layout_person_of_movie_recycler
        const val LAYOUT_SIMILAR = R.layout.layout_similar_recycler
    }
}

interface MovieDetailsInteractListener : BaseInteractListener {
    fun onClickSeeAllPersons()
    fun onClickPersonItem(id: Int)
    fun onClickSeeAllSimilar()
    fun onClickDownloadImage(bitmap: Bitmap)

}