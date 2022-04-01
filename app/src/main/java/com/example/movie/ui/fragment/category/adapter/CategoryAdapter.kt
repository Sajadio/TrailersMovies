package com.example.movie.ui.fragment.category.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.data.model.Category
import com.example.movie.databinding.LayoutItemCardCategoryBinding
import com.example.movie.databinding.LayoutRvCategoryBinding
import com.example.movie.databinding.LayoutViewMoreCategoryBinding
import com.example.movie.ui.fragment.home.adapter.HomeAdapter
import com.example.movie.ui.fragment.home.adapter.OnClickListener
import com.example.movie.utils.ViewType
import com.example.movie.utils.loadImage

class CategoryAdapter(
    private val list: List<Category>,
    private val listener: OnClickListener,
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val v = layoutInflater.inflate(R.layout.layout_item_card_category, parent, false)

        return CategoryViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        bindCategory(holder, position)
    }

    private fun bindCategory(holder: CategoryViewHolder, position: Int) {
        val category = list[position]
        holder.binding.apply {
            posterCategory.loadImage(category.posterId)
            titleMS.text = category.title
            category.type.type.forEach {
                type.text = "${it}, "
            }
            rating.rating = category.rate

            cardCategory.setOnClickListener {
                listener.categoryItem(category)
            }
        }
    }


    override fun getItemCount() = list.size

    inner class CategoryViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val binding = LayoutItemCardCategoryBinding.bind(itemView)
    }

}
