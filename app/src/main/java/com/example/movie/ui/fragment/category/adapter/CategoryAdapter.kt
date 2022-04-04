package com.example.movie.ui.fragment.category.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.data.model.Category
import com.example.movie.databinding.LayoutItemCardCategoryBinding
import com.example.movie.ui.fragment.home.adapter.OnClickListener
import com.example.movie.utils.loadImage

class CategoryAdapter(
    private var list: List<Category>,
    private val listener: OnClickListener,
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.layout_item_card_category, parent, false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = list[position]
        holder.binding.apply {
            posterCategory.loadImage(category.posterId)
            titleMS.text = category.title
            category.type.type.forEach {
                type.text = "${it}, "
            }
            rating.rating = category.rate
        }
    }

    override fun getItemCount() = list.size

    inner class CategoryViewHolder(val binding: LayoutItemCardCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }


}
