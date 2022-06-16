package com.example.trailers.ui.fragment.genres.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.trailers.data.model.movie.genremovie.MovieResult
import com.example.trailers.databinding.LayoutItemCardGenresPagingBinding
import com.example.trailers.ui.fragment.home.adapter.OnClickListener

class GenresPagingAdapter: PagingDataAdapter<MovieResult, GenresPagingAdapter.GenresHolder>(CharacterComparator) {

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
        fun bind(item: MovieResult) = apply {
            binding.apply {
                genre = item
                root.setOnClickListener {
                    onItemClickListener?.let { it(item.id) }
                }
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