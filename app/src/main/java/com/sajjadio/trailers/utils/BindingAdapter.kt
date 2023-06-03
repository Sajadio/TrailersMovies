package com.sajjadio.trailers.utils

import android.annotation.SuppressLint
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
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
fun <T> View.manageState(state: NetworkStatus<T>?) {
    if (state is NetworkStatus.Error)
        this.visibility = VISIBLE
    else
        this.visibility = INVISIBLE
}

