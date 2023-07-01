package com.sajjadio.trailers.ui.person.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import com.sajjadio.trailers.BR
import com.sajjadio.trailers.R
import com.sajjadio.trailers.domain.model.CommonResult
import com.sajjadio.trailers.domain.model.Person
import com.sajjadio.trailers.domain.model.Poster
import com.sajjadio.trailers.ui.base.BaseAdapter
import com.sajjadio.trailers.ui.base.BaseInteractListener
import com.sajjadio.trailers.ui.person.utils.PersonDetailsItem

class PersonAdapter(
    val listener: PersonDetailsInteractListener
) : BaseAdapter<PersonDetailsItem>(listOf(), listener) {

    override var layoutId: Int = 0

    @SuppressLint("NotifyDataSetChanged")
    fun addNestedItem(newItem: List<PersonDetailsItem>) {
        setItems(newItem.sortedBy { it.rank })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        layoutId = getLayoutId(viewType)
        return super.onCreateViewHolder(parent, viewType)
    }

    private fun getLayoutId(viewType: Int): Int {
        return when (viewType) {
            LAYOUT_PERSON -> LAYOUT_PERSON
            LAYOUT_GALLERY -> LAYOUT_GALLERY
            LAYOUT_MOVIE -> LAYOUT_MOVIE
            else -> throw Exception("unknown view type")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (val currentItem = getItems()[position]) {
            is PersonDetailsItem.PersonItem -> {
                bindPersonItem(holder as ItemViewHolder, currentItem.person)
            }

            is PersonDetailsItem.GalleryOFPersonItem -> {
                bindGalleryItem(holder as ItemViewHolder, currentItem.poster)
            }

            is PersonDetailsItem.MoviesOfPersonItem -> {
                bindMovieItem(holder as ItemViewHolder, currentItem.commonResult)
            }
        }
    }

    private fun bindPersonItem(holder: ItemViewHolder, item: Person) {
        holder.binding.setVariable(BR.item, item)
    }

    private fun bindGalleryItem(holder: ItemViewHolder, items: List<Poster>) {
        holder.binding.apply {
            setVariable(BR.adapter, GalleryOfPersonAdapter(items, listener))
            setVariable(BR.listener, listener)
        }
    }

    private fun bindMovieItem(holder: ItemViewHolder, items: List<CommonResult>) {
        holder.binding.apply {
            setVariable(BR.adapter, MoviesOfPersonAdapter(items, listener))
            setVariable(BR.listener, listener)
        }
    }


    override fun getItemViewType(position: Int): Int {
        return when (getItems()[position]) {
            is PersonDetailsItem.PersonItem -> LAYOUT_PERSON
            is PersonDetailsItem.GalleryOFPersonItem -> LAYOUT_GALLERY
            is PersonDetailsItem.MoviesOfPersonItem -> LAYOUT_MOVIE
        }
    }

    private companion object {
        const val LAYOUT_PERSON = R.layout.layout_person_details_item
        const val LAYOUT_GALLERY = R.layout.layout_gallery_of_person_recycler
        const val LAYOUT_MOVIE = R.layout.layout_movies_of_person_recycler
    }
}

interface PersonDetailsInteractListener : BaseInteractListener{
    fun onClickGalleryItem()
    fun onClickSeeAllGallery()
    fun onClickMovieItem(id: Int)
    fun onClickSeeAllMovies()
}