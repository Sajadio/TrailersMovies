package com.example.movie.ui.fragment.genres.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.data.m.Genres
import com.example.movie.databinding.LayoutItemCardGenresBinding
import com.example.movie.ui.fragment.home.adapter.OnClickListener
import com.example.movie.utils.loadImage

class SubGenresAdapter(
    private var list: List<Genres>,
    private val listener: OnClickListener,
) : RecyclerView.Adapter<SubGenresAdapter.GeneresViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneresViewHolder {
        return GeneresViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.layout_item_card_genres, parent, false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: GeneresViewHolder, position: Int) {
        val category = list[position]
        holder.binding.apply {
            posterGeneres.loadImage(category.posterId)
            titleMS.text = category.title
            category.type.type.forEach {
                type.text = "${it}, "
            }
            rating.rating = category.rate
            cardGeneres.setOnClickListener {
//                transformationLayout.startTransform()
            }
        }
    }

    override fun getItemCount() = list.size

    inner class GeneresViewHolder(val binding: LayoutItemCardGenresBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }


}
