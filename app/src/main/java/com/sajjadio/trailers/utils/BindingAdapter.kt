package com.sajjadio.trailers.utils

import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.sajjadio.trailers.data.model.movie.movie_details.Genre
import com.sajjadio.trailers.ui.base.BaseAdapter


@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    (view.adapter as BaseAdapter<T>).setItems(items ?: emptyList())
}


@BindingAdapter("app:loading")
fun loading(progress: ProgressBar, state: NetworkStatus<Any>?) {
    state?.let {
        progress.isVisible = (it is NetworkStatus.Loading)
    }
}

@BindingAdapter(value = ["app:imageUrl", "app:imageSize"])
fun setImage(imageView: ImageView, url: String?, imageSize: String?) {
    url?.let { imageView.loadImage(it, imageSize) }
}

@BindingAdapter(value = ["app:manageState"])
fun <T> manageState(view: View, state: NetworkStatus<T>?) {
    if (state is NetworkStatus.Error)
        view.visibility = VISIBLE
    else
        view.visibility = INVISIBLE
}

@BindingAdapter(value = ["app:list"])
fun setGenres(textView: TextView, genres: List<Genre>?) {
    var genre = ""
    genres?.forEach {
        genre += if (it != genres.last())
            "${it.name} | "
        else
            it.name
    }
    textView.text = genre
}

@BindingAdapter(value = ["app:shimmer"])
fun <T : Any> manageShimmerFrameLayout(shimmer: ShimmerFrameLayout, data: PagingData<T>?) {
    if (data != null) {
        shimmer.stopShimmer()
    } else {
        shimmer.startShimmer()
    }
}
