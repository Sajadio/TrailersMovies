package com.example.movie.ui.base.nestedrv

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.data.model.trend.Trending
import com.example.movie.databinding.*
import com.example.movie.ui.fragment.home.adapter.OnClickListener
import com.example.movie.ui.fragment.home.adapter.TrendAdapter
import com.example.movie.utils.ListHomeAdapterItem
import com.example.movie.utils.ViewTypeHome
import com.yarolegovich.discretescrollview.transform.ScaleTransformer

class HomeAdapter(
    private var data: List<ListHomeAdapterItem<Any>>,
    private val listener: OnClickListener,
) : RecyclerView.Adapter<HomeAdapter.BaseViewHolder>() {


    @SuppressLint("NotifyDataSetChanged")
    fun updateData(list: List<ListHomeAdapterItem<Any?>>) {
        this.data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val v = layoutInflater.inflate(viewType, parent, false)

        return when (viewType) {
            TREND -> TrendViewHolder(v)
            VIEW_MORE_GENERES -> ViewMoreViewHolderGenres(v)
            GENERES -> SubGenresViewHolder(v)
            VIEW_MORE_POPULAR -> ViewMorePopularViewHolder(v)
            POPULAR -> PopularViewHolder(v)
            else -> throw Exception("UNKNOWN VIEW TYPE")
        }

    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is TrendViewHolder -> bindTrending(holder, position)
            is ViewMoreViewHolderGenres -> bindViewMoreCategory(holder, position)
            is SubGenresViewHolder -> bindGeneres(holder, position)
            is ViewMorePopularViewHolder -> bindViewMorePopular(holder, position)
            is PopularViewHolder -> bindPopular(holder, position)
        }
    }


    private fun bindTrending(holder: TrendViewHolder, position: Int) {
        val adapter = TrendAdapter(emptyList())
        holder.binding.apply {
            trend = data[position].item as Trending
            rvTrend.adapter = adapter
            rvTrend.setItemTransformer(
                ScaleTransformer.Builder()
                    .setMaxScale(1.0f)
                    .setMinScale(0.8f)
                    .build()
            )
        }
    }

    private fun bindViewMoreCategory(holder: ViewMoreViewHolderGenres, position: Int) {
        holder.binding.viewMoreGeneres.setOnClickListener {
//            listener.category(1)
        }
    }

    private fun bindGeneres(holder: SubGenresViewHolder, position: Int) {
//        val categoryList = data[position].item as List<Genres>
//        val adapter = SubGenresAdapter(categoryList, listener)
//        holder.binding.apply {
//            recyclerViewCategory.adapter = adapter
//            recyclerViewCategory.setHasFixedSize(true)
//        }
    }


    private fun bindViewMorePopular(holder: ViewMorePopularViewHolder, position: Int) {
        holder.binding.viewMorePopular.setOnClickListener {
            listener.popular(1)
        }
    }

    private fun bindPopular(holder: PopularViewHolder, position: Int) {

    }


    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int) = when (data[position].typeHome) {
        ViewTypeHome.TREND -> TREND
        ViewTypeHome.VIEW_MORE_POPULAR -> VIEW_MORE_POPULAR
        ViewTypeHome.POPULAR -> POPULAR
        ViewTypeHome.VIEW_MORE_TOP_RATED -> POPULAR
        ViewTypeHome.RATED -> POPULAR
        ViewTypeHome.VIEW_MORE_UPCOMING -> POPULAR
        ViewTypeHome.UPCOMING -> POPULAR
    }

    abstract class BaseViewHolder(binder: View) :
        RecyclerView.ViewHolder(binder)

    inner class TrendViewHolder(itemView: View) :
        BaseViewHolder(itemView) {
        val binding = LayoutRvTrendItemBinding.bind(itemView)
    }

    inner class ViewMoreViewHolderGenres(itemView: View) :
        BaseViewHolder(itemView) {
        val binding = LayoutViewMoreGenresBinding.bind(itemView)
    }

    inner class SubGenresViewHolder(itemView: View) :
        BaseViewHolder(itemView) {
        val binding = LayoutRvGenresBinding.bind(itemView)
    }

    inner class ViewMorePopularViewHolder(itemView: View) :
        BaseViewHolder(itemView) {
        val binding = LayoutViewMorePopularBinding.bind(itemView)
    }


    inner class PopularViewHolder(itemView: View) :
        BaseViewHolder(itemView) {
        val binding = LayoutItemCardCommenBinding.bind(itemView)
    }

    companion object {
        const val TREND = R.layout.layout_rv_trend_item
        const val VIEW_MORE_GENERES = R.layout.layout_view_more_genres
        const val GENERES = R.layout.layout_rv_genres
        const val VIEW_MORE_POPULAR = R.layout.layout_view_more_popular
        const val POPULAR = R.layout.layout_item_card_commen

    }


}
