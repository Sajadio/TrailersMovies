package com.example.trailers.ui.fragment.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.trailers.data.model.movie.similar.Result
import com.example.trailers.databinding.LayoutCardSimilarBinding
import com.example.trailers.ui.fragment.home.adapter.OnClickListener

class SimilarPagingAdapter(
    private val listener: OnClickListener,
) :
    PagingDataAdapter<Result, SimilarPagingAdapter.SimilarHolder>(CharacterComparator) {


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
        val item = getItem(position)
        holder.binding.apply {
            similar = item
            root.setOnClickListener {
                listener.clickItem(item?.id)
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