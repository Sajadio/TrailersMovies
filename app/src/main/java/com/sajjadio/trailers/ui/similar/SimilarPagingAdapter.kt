package com.sajjadio.trailers.ui.similar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sajjadio.trailers.data.model.movie.similar.SimilarResult
import com.sajjadio.trailers.databinding.LayoutCardSimilarBinding

class SimilarPagingAdapter :
    PagingDataAdapter<SimilarResult, SimilarPagingAdapter.SimilarHolder>(CharacterComparator) {

    private var onItemClickListener: ((Int?) -> Unit)? = null
    fun onItemClickListener(listener: (Int?) -> Unit) {
        onItemClickListener = listener
    }

    inner class SimilarHolder(val binding: LayoutCardSimilarBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    object CharacterComparator : DiffUtil.ItemCallback<SimilarResult>() {
        override fun areItemsTheSame(oldItem: SimilarResult, newItem: SimilarResult) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: SimilarResult, newItem: SimilarResult) =
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