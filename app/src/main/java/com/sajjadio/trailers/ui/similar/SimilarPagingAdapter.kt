package com.sajjadio.trailers.ui.similar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sajjadio.trailers.data.model.movie.similar.Result
import com.sajjadio.trailers.databinding.LayoutCardSimilarBinding

class SimilarPagingAdapter :
    PagingDataAdapter<Result, SimilarPagingAdapter.SimilarHolder>(CharacterComparator) {

    private var onItemClickListener: ((Int?) -> Unit)? = null
    fun onItemClickListener(listener: (Int?) -> Unit) {
        onItemClickListener = listener
    }

    inner class SimilarHolder(val binding: LayoutCardSimilarBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    object CharacterComparator : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: Result, newItem: Result) =
            oldItem == newItem
    }

    override fun onBindViewHolder(holder: SimilarHolder, position: Int) {
        val similar = getItem(position)
        holder.binding.apply {
              item = similar
            root.setOnClickListener {
                onItemClickListener?.let { it(similar?.id) }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SimilarHolder(
            LayoutCardSimilarBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

}