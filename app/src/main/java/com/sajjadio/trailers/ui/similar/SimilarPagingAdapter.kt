package com.sajjadio.trailers.ui.similar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sajjadio.trailers.databinding.LayoutCardSimilarBinding
import com.sajjadio.trailers.databinding.LayoutNormalCommonCardBinding
import com.sajjadio.trailers.domain.model.CommonResult
import com.sajjadio.trailers.ui.base.BaseInteractListener

class SimilarPagingAdapter(
    private val _listener: BaseInteractListener
) :
    PagingDataAdapter<CommonResult, SimilarPagingAdapter.SimilarHolder>(CharacterComparator) {

    private var onItemClickListener: ((Int?) -> Unit)? = null
    fun onItemClickListener(listener: (Int?) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SimilarHolder(
            LayoutNormalCommonCardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: SimilarHolder, position: Int) {
        val similar = getItem(position)
        holder.binding.apply {
            item = similar
            listener = _listener
        }
    }

    inner class SimilarHolder(val binding: LayoutNormalCommonCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    object CharacterComparator : DiffUtil.ItemCallback<CommonResult>() {
        override fun areItemsTheSame(oldItem: CommonResult, newItem: CommonResult) =
            oldItem.original_title == newItem.original_title

        override fun areContentsTheSame(oldItem: CommonResult, newItem: CommonResult) =
            oldItem == newItem
    }

}