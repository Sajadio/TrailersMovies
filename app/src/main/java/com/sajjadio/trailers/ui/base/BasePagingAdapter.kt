package com.sajjadio.trailers.ui.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sajjadio.trailers.BR

abstract class BasePagingAdapter<T : Any>(
    private val listener: BaseInteractListener,
) : PagingDataAdapter<T, BasePagingAdapter.BasePagingViewHolder>(BaseItemComparator()) {

    @get:LayoutRes
    abstract var layoutId: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasePagingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ViewDataBinding>(inflater, layoutId, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: BasePagingViewHolder, position: Int) {
        val currentItem = getItem(position)
        when (holder) {
            is ItemViewHolder -> {
                holder.binding.setVariable(BR.item, currentItem)
                holder.binding.setVariable(BR.listener, listener)
            }
        }
    }

    abstract class BasePagingViewHolder(binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root)

    open class ItemViewHolder(val binding: ViewDataBinding) : BasePagingViewHolder(binding)

    class BaseItemComparator<T : Any> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }
    }

}

