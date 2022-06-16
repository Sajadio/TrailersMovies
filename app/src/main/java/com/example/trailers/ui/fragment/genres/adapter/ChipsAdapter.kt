package com.example.trailers.ui.fragment.genres.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.trailers.R
import com.example.trailers.data.model.genre.Genres
import com.example.trailers.data.model.movie.genremovie.MovieResult
import com.example.trailers.data.model.movie.trend.TrendResult
import com.example.trailers.databinding.LayoutChipsItemBinding
import com.example.trailers.ui.base.adapter.BaseAdapter
import com.example.trailers.ui.fragment.home.adapter.MultiTypeViewAdapter
import com.example.trailers.ui.fragment.home.adapter.OnClickListener
import com.example.trailers.utils.ParentListAdapter
import kotlinx.android.synthetic.main.fragment_home.view.*

class ChipsAdapter(
    private val data:List<Genres>
): RecyclerView.Adapter<ChipsAdapter.ChipsHolder>() {

    val differ = AsyncListDiffer(this, DifferCallbacks)
    private var selectedPosition: Int = 0

    private var onItemClickListener: ((String?) -> Unit)? = null
    fun onItemClickListener(listener: (String?) -> Unit) {
        onItemClickListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ChipsHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.layout_chips_item,
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: ChipsHolder,
        @SuppressLint("RecyclerView") position: Int,
    ) {
        val item = data[position]
        holder.binding.apply {
            textChip.text = item.name

            if (selectedPosition == 0) {
                onItemClickListener?.let { it("28") }
            }
            if (selectedPosition == position) {
                textChip.changeColor(R.color.redLight, "#ffffff")
            } else {
                textChip.changeColor(R.color.gryLight, "#000000")
                root.setOnClickListener {
                    onItemClickListener?.let { it(item.id.toString()) }

                    if (selectedPosition >= 0)
                        notifyItemChanged(selectedPosition)
                    selectedPosition = holder.layoutPosition
                    notifyItemChanged(selectedPosition)
                }
            }
        }
    }

    private fun TextView.changeColor(backgroundColor: Int, textColor: String) {
        this.setBackgroundResource(backgroundColor)
        this.setTextColor(Color.parseColor(textColor))
    }

    override fun getItemCount() = data.size

    inner class ChipsHolder(val binding: LayoutChipsItemBinding) : RecyclerView.ViewHolder(binding.root)


    object DifferCallbacks : DiffUtil.ItemCallback<Genres>() {
        override fun areItemsTheSame(oldItem: Genres, newItem: Genres) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Genres, newItem: Genres) =
            oldItem == newItem
    }

}