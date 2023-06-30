package com.sajjadio.trailers.ui.common

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sajjadio.trailers.data.model.movie.common.CommonResultDto
import com.sajjadio.trailers.databinding.LayoutItemCardCommonPagingBinding
import com.sajjadio.trailers.utils.Constant
import com.sajjadio.trailers.utils.loadImage

class CommonPagingAdapter : PagingDataAdapter<CommonResultDto, CommonPagingAdapter.CommonHolder>(
    CharacterComparator
) {

    private var onItemClickListener: ((Int?) -> Unit)? = null
    fun onItemClickListener(listener: (Int?) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CommonHolder(
            LayoutItemCardCommonPagingBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: CommonHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


    inner class CommonHolder(private val binding: LayoutItemCardCommonPagingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(common: CommonResultDto) = apply {
            binding.apply {
                item = common
                imageViewMovie.loadImage(common.poster_path.toString(), Constant.IMAGE_Size_500)
            }
        }
    }

    object CharacterComparator : DiffUtil.ItemCallback<CommonResultDto>() {
        override fun areItemsTheSame(oldItem: CommonResultDto, newItem: CommonResultDto) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: CommonResultDto, newItem: CommonResultDto) =
            oldItem == newItem
    }
}