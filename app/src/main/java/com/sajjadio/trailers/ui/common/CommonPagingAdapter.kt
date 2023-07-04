package com.sajjadio.trailers.ui.common

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sajjadio.trailers.data.model.movie.common.CommonResultDto
import com.sajjadio.trailers.databinding.LayoutItemCardCommonPagingBinding
import com.sajjadio.trailers.databinding.LayoutNormalCommonCardBinding
import com.sajjadio.trailers.databinding.LayoutSmallCommonCardBinding
import com.sajjadio.trailers.domain.model.CommonResult
import com.sajjadio.trailers.ui.base.BaseInteractListener
import com.sajjadio.trailers.utils.Constant
import com.sajjadio.trailers.utils.loadImage

class CommonPagingAdapter(
    private val _listener: BaseInteractListener
) : PagingDataAdapter<CommonResult, CommonPagingAdapter.CommonHolder>(
    CharacterComparator
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CommonHolder(
            LayoutNormalCommonCardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: CommonHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


    inner class CommonHolder(private val binding: LayoutNormalCommonCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(common: CommonResult) = apply {
            binding.apply {
                item = common
                listener = _listener
            }
        }
    }

    object CharacterComparator : DiffUtil.ItemCallback<CommonResult>() {
        override fun areItemsTheSame(oldItem: CommonResult, newItem: CommonResult) =
            oldItem.original_title == newItem.original_title

        override fun areContentsTheSame(oldItem: CommonResult, newItem: CommonResult) =
            oldItem == newItem
    }
}