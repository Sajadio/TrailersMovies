package com.example.movie.ui.fragment.home.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.data.model.*
import com.example.movie.databinding.*
import com.example.movie.ui.fragment.category.adapter.CategoryAdapter
import com.example.movie.utils.ListAdapterItem
import com.example.movie.utils.ViewType
import com.example.movie.utils.loadImage
import com.google.android.material.chip.Chip
import com.opensooq.pluto.base.PlutoAdapter
import com.opensooq.pluto.listeners.OnItemClickListener
import com.opensooq.pluto.listeners.OnSlideChangeListener

class HomeAdapter(
    private var data: List<ListAdapterItem<Any>>,
    private val lifecycle: Lifecycle,
    private val listener: OnClickListener
) : RecyclerView.Adapter<HomeAdapter.BaseViewHolder>() {

//    @SuppressLint("NotifyDataSetChanged")
//    fun updateData(list: List<UIModel>) {
//        this.data = list
//        notifyDataSetChanged()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val v = layoutInflater.inflate(viewType, parent, false)

        return when (viewType) {
            TREND -> TrendViewHolder(v)
            CHIPS_TYPE -> TypeViewHolder(v, layoutInflater)
            VIEW_MORE_CATEGORY -> ViewMoreViewHolderCategory(v)
            CATEGORY -> CategoryViewHolder(v)
            VIEW_MORE_POPULAR -> ViewMorePopularViewHolder(v)
            POPULAR -> PopularViewHolder(v)
            else -> throw Exception("UNKNOWN VIEW TYPE")
        }

    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is TrendViewHolder -> bindTrending(holder, position)
            is TypeViewHolder -> bindTyping(holder, position)
            is ViewMoreViewHolderCategory -> bindViewMoreCategory(holder, position)
            is CategoryViewHolder -> bindCategory(holder, position)
            is ViewMorePopularViewHolder -> bindViewMorePopular(holder, position)
            is PopularViewHolder -> bindPopular(holder, position)
        }
    }


    private fun bindTrending(holder: TrendViewHolder, position: Int) {
        val trendList = data[position].item as MutableList<Trend>
        holder.binding.apply {
            val pluto = sliderView
            val adapter = SliderAdapter(trendList, object : OnItemClickListener<Trend> {
                override fun onItemClicked(item: Trend?, position: Int) {
                    Log.d("sajjadio", "on slide change $position ")
                }
            })

            pluto.create(adapter, 4000, lifecycle = lifecycle)
            pluto.setCustomIndicator(customIndicator)
            pluto.setOnSlideChangeListener(object : OnSlideChangeListener {
                override fun onSlideChange(adapter: PlutoAdapter<*, *>, position: Int) {
                }
            })

        }
    }

    @SuppressLint("ResourceAsColor")
    private fun bindTyping(holder: TypeViewHolder, position: Int) {
        val typeList = data[position].item as Type
        holder.binding.apply {
            typeList.type.forEach { chipsText ->
                val chipGroup = chipGroupTyping
                val chip = holder.layoutInflater.inflate(
                    R.layout.layout_chips_item_type,
                    chipGroup,
                    false
                ) as Chip
                chip.setOnClickListener {
                    it.setBackgroundColor(Color.BLACK)
                }
                chip.text = chipsText
                chipGroup.addView(chip)
            }
        }

    }


    private fun bindViewMoreCategory(holder: HomeAdapter.ViewMoreViewHolderCategory, position: Int) {
        holder.binding.viewMoreCategory.setOnClickListener {
            listener.category(1)
        }
    }

    private fun bindCategory(holder: CategoryViewHolder, position: Int) {
        val categoryList = data[position].item as List<Category>
        val adapter = CategoryAdapter(categoryList, listener)
        holder.binding.apply {
            recyclerViewCategory.adapter = adapter
            recyclerViewCategory.setHasFixedSize(true)
        }
    }


    private fun bindViewMorePopular(holder: HomeAdapter.ViewMorePopularViewHolder, position: Int) {
//        holder.binding.viewpopular.setOnClickListener {
//            listener.popularMove(data[position].item as List<Popular>)
//        }
    }

    private fun bindPopular(holder: PopularViewHolder, position: Int) {
        val popular = data[position].item as Popular
        holder.binding.apply {
            posterPopular.loadImage(popular.posterId)
            titleMS.text = popular.title
            popular.type.type.forEach {
                type.text = "${it}, "
            }
            rating.rating = popular.rate
            date.text = "2022"
        }
    }


    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int) = when (data[position].type) {
        ViewType.TREND -> TREND
        ViewType.CHIPS_TYPE -> CHIPS_TYPE
        ViewType.VIEW_MORE_CATEGORY -> VIEW_MORE_CATEGORY
        ViewType.CATEGORY -> CATEGORY
        ViewType.VIEW_MORE_POPULAR -> VIEW_MORE_POPULAR
        ViewType.POPULAR -> POPULAR
    }

    abstract class BaseViewHolder(binder: View) :
        RecyclerView.ViewHolder(binder)

    inner class TrendViewHolder(itemView: View) :
        BaseViewHolder(itemView) {
        val binding = LayoutRvTrendItemBinding.bind(itemView)
    }

    inner class TypeViewHolder(
        itemView: View, val layoutInflater: LayoutInflater
    ) :
        BaseViewHolder(itemView) {
        val binding = LayoutChipsGroupTypeBinding.bind(itemView)
    }

    inner class ViewMoreViewHolderCategory(itemView: View) :
        BaseViewHolder(itemView) {
        val binding = LayoutViewMoreCategoryBinding.bind(itemView)
    }

    inner class CategoryViewHolder(itemView: View) :
        BaseViewHolder(itemView) {
        val binding = LayoutRvCategoryBinding.bind(itemView)
    }

    inner class ViewMorePopularViewHolder(itemView: View) :
        BaseViewHolder(itemView) {
        val binding = LayoutViewMorePopularBinding.bind(itemView)
    }


    inner class PopularViewHolder(itemView: View) :
        BaseViewHolder(itemView) {
        val binding = LayoutItemCardPopularBinding.bind(itemView)
    }

    companion object {
        const val TREND = R.layout.layout_rv_trend_item
        const val CHIPS_TYPE = R.layout.layout_chips_group_type
        const val VIEW_MORE_CATEGORY = R.layout.layout_view_more_category
        const val CATEGORY = R.layout.layout_rv_category
        const val VIEW_MORE_POPULAR = R.layout.layout_view_more_popular
        const val POPULAR = R.layout.layout_item_card_popular

    }


}
