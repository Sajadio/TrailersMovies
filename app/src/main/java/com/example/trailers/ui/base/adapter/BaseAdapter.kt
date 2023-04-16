package com.example.trailers.ui.base.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.trailers.utils.ParentListAdapter

abstract class BaseAdapter<BINDING : ViewDataBinding, T : ParentListAdapter>(
    private val list: List<ParentListAdapter>,
) :
    RecyclerView.Adapter<BaseAdapter.BaseViewHolder<BINDING>>() {
    @get:LayoutRes
    abstract val layoutId: Int

    abstract fun bind(binding: BINDING, item1: Int, item: T)

    private val differ = AsyncListDiffer(this, DifferCallbacks)

    init {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BaseViewHolder(DataBindingUtil.inflate<BINDING>(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: BaseViewHolder<BINDING>, position: Int) {
            bind(holder.binder,holder.layoutPosition, differ.currentList[position] as T)
    }

    override fun getItemCount(): Int = differ.currentList.size

    object DifferCallbacks : DiffUtil.ItemCallback<ParentListAdapter>() {
        override fun areItemsTheSame(
            oldItem: ParentListAdapter,
            newItem: ParentListAdapter,
        ) =
            oldItem == newItem

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: ParentListAdapter,
            newItem: ParentListAdapter,
        ) =
            oldItem == newItem
    }

    class BaseViewHolder<BINDING : ViewDataBinding>(val binder: BINDING) : RecyclerView.ViewHolder(binder.root)
}

