package com.example.movie.ui.base.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.utils.ParentListAdapter

abstract class BaseAdapter<BINDING : ViewDataBinding, T : ParentListAdapter>(
    private var data: List<T>,
) : RecyclerView.Adapter<BaseViewHolder<BINDING>>() {
    @get:LayoutRes
    abstract val layoutId: Int

    abstract fun bind(binding: BINDING, item: T)

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(list: List<T>) {
        this.data = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BINDING> {
        val binder = DataBindingUtil.inflate<BINDING>(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        )
        return BaseViewHolder(binder)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<BINDING>, position: Int) {
        bind(holder.binder, data[position])
    }

    override fun getItemCount(): Int = data.size

}

