package com.sajjadio.trailers.ui.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T>(
    private var items: List<T>,
    private val listener: BaseInteractListener
) : RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {

    @get:LayoutRes
    abstract var layoutId: Int

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(newItems: List<T>) {
        val differ = DiffUtil.calculateDiff(MovieDiffUtil(items, newItems) { oldItem, newItem ->
            areItemsSame(oldItem, newItem)
        })
        items = newItems
        differ.dispatchUpdatesTo(this)
    }

    open fun areItemsSame(
        oldItem: T,
        newItem: T
    ) = oldItem?.equals(oldItem) == true

    fun getItems() = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ViewDataBinding>(inflater, layoutId, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentItem = items[position]
        when (holder) {
            is ItemViewHolder -> {
//                holder.binding.setVariable(BR.item, currentItem)
//                holder.binding.setVariable(BR.listener, listener)
            }
        }
    }

    override fun getItemCount() = items.size

    abstract class BaseViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
    open class ItemViewHolder(val binding: ViewDataBinding) : BaseViewHolder(binding)
}

interface BaseInteractListener