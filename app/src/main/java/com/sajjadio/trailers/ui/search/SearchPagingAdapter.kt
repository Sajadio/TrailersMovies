package com.sajjadio.trailers.ui.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sajjadio.trailers.data.model.movie.search.Result
import com.sajjadio.trailers.databinding.LayoutSearchBinding

class SearchPagingAdapter :
    PagingDataAdapter<Result, SearchPagingAdapter.SearchViewHolder>(CharacterComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SearchViewHolder(
            LayoutSearchBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    private var onItemClickListener: ((Int?) -> Unit)? = null
    fun onItemClickListener(listener: (Int?) -> Unit) {
        onItemClickListener = listener
    }

    inner class SearchViewHolder(private val binding: LayoutSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(search: Result) = apply {
            binding.apply {
                 item = search
                root.setOnClickListener {
                    onItemClickListener?.let { (it(search.id)) }
                }
                executePendingBindings()
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