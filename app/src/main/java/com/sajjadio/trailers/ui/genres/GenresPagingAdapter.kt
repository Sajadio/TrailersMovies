package com.sajjadio.trailers.ui.genres

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sajjadio.trailers.data.model.movie.genremovie.MovieResult
import com.sajjadio.trailers.databinding.LayoutItemCardGenresPagingBinding

class GenresPagingAdapter: PagingDataAdapter<MovieResult, GenresPagingAdapter.GenresHolder>(
    CharacterComparator
) {

    private var onItemClickListener: ((Int?) -> Unit)? = null
    fun onItemClickListener(listener: (Int?) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GenresHolder(
            LayoutItemCardGenresPagingBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: GenresHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


    inner class GenresHolder(private val binding: LayoutItemCardGenresPagingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(genre: MovieResult) = apply {
            binding.apply {
                 item = genre
                root.setOnClickListener {
                    onItemClickListener?.let { it(genre.id) }
                }
                executePendingBindings()
            }
        }
    }

    object CharacterComparator : DiffUtil.ItemCallback<MovieResult>() {
        override fun areItemsTheSame(oldItem: MovieResult, newItem: MovieResult) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MovieResult, newItem: MovieResult) =
            oldItem == newItem
    }

}