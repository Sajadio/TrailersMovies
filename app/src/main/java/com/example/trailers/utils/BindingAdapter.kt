package com.example.trailers.utils

import android.annotation.SuppressLint
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.trailers.data.model.genre.Genre
import com.example.trailers.ui.base.adapter.BaseAdapter
import com.example.trailers.ui.fragment.home.adapter.ParentAdapter

@BindingAdapter("app:setAdapter")
fun setAdapter(
    recyclerView: RecyclerView,
    adapter: ParentAdapter?,
) {
    adapter?.let {
        recyclerView.adapter = it
        recyclerView.setHasFixedSize(true)
    }
}


@Suppress("UNCHECKED_CAST")
@BindingAdapter("app:submitList")
fun submitList(recyclerView: RecyclerView, list: List<ParentListAdapter>?) {
    val adapter = recyclerView.adapter as BaseAdapter<ViewDataBinding, ParentListAdapter>
    list?.let {
        adapter.updateData(it)
    }
}


@BindingAdapter("app:loading")
fun <T> loading(progressBar: ProgressBar, state: NetworkStatus<T>?) {
    when (state) {
        is NetworkStatus.Loading -> progressBar.visibility = VISIBLE
        is NetworkStatus.Success -> progressBar.visibility = INVISIBLE
        is NetworkStatus.Error -> progressBar.visibility = INVISIBLE
        else -> {
            progressBar.visibility = INVISIBLE
        }
    }
}

@BindingAdapter("app:manageState")
fun <T> SwipeRefreshLayout.manageState(state: NetworkStatus<T>?) {
    when (state) {
        is NetworkStatus.Loading -> this.isRefreshing = true
        is NetworkStatus.Success -> this.isRefreshing = false
        is NetworkStatus.Error -> this.isRefreshing = false
        else -> {
            this.isRefreshing = false
        }
    }
}


@BindingAdapter(value = ["app:setImage"])
fun ImageView.setImage(url: String?) {
    url?.let { this.loadImage(it) }
}


@BindingAdapter(value = ["app:setText"])
fun TextView.setText(text: String?) {
    this.text = text
}

@BindingAdapter(value = ["app:setGenres"])
fun TextView.setGenres(genre: List<Genre>?) {
    this.text = genre?.first()?.name
}

@SuppressLint("SetTextI18n")
@BindingAdapter(value = ["app:vote_count"])
fun TextView.voteCount(text: Int?) {
    this.text = "(${text?.format()})"
}

@BindingAdapter(value = ["app:setDate"])
fun TextView.setDate(text: String?) {
    this.text = text?.substring(0, 4)
}


@BindingAdapter(value = ["app:setRate"])
fun TextView.setRate(rating: Double?) {
    rating?.let { this.text = it.toString() }
}

//
//@BindingAdapter(value = ["app:chips"])
//fun ChipGroup.setChips(chipText: List<String>?) {
//
//    val chip = LayoutInflater.from(context).inflate(R.layout.layout_chips, this, false) as Chip
//    chipText?.forEach { chip.text = it }
//    this.addView(chip)
//}

//@BindingAdapter("manageState")
//fun manageState(progressBar: ProgressBar, state: Boolean) {
//    progressBar.visibility = if (state) View.VISIBLE else View.GONE
//}
//


//
//@BindingAdapter("setFavouriteCondition")
//fun setFavouriteCondition(imageView: ShapeableImageView, isFavourite: Boolean) {
//    if (isFavourite) {
//        imageView.setImageResource(R.drawable.ic_favorite)
//    } else {
//        imageView.setImageResource(R.drawable.ic_favorite_border)
//    }
//
//}