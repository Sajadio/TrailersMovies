package com.example.trailers.ui.fragment.search.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.trailers.R
import com.example.trailers.data.model.genre.Genre
import com.example.trailers.data.model.search.Result
import com.example.trailers.databinding.LayoutSearchBinding
import com.example.trailers.utils.Constant.genresMovie
import com.example.trailers.utils.loadImage

class SearchPagingAdapter(private var list: List<Genre>) :
    PagingDataAdapter<Result, SearchPagingAdapter.SearchViewHolder>(CharacterComparator) {

    fun setGenres(genre: List<Genre>) {
        list = genre
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SearchViewHolder(
            LayoutSearchBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


    inner class SearchViewHolder(private val binding: LayoutSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: Result) = apply {
            binding.apply {
                poster.loadImage(item.poster_path.toString())
                item.vote_average?.let {
                    ratingBar.rating.rating = it.toFloat().div(2)
                    ratingBar.textRating.text = it.div(2).toString()
                }
                if (item.media_type == "movie") {
                    title.text = item.original_title
                    mediaType.text = itemView.resources.getString(R.string.movie)
                    date.text = item.release_date

                } else if (item.media_type == "tv") {
                    title.text = item.original_name
                    mediaType.text = itemView.resources.getString(R.string.series)
                    date.text = item.first_air_date
                }

            }
        }
    }

    object CharacterComparator : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: Result, newItem: Result) =
            oldItem == newItem
    }

}