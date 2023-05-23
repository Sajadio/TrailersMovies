package com.sajjadio.trailers.utils

import android.annotation.SuppressLint
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sajjadio.trailers.ui.base.BaseAdapter


@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    items?.let {
        (view.adapter as BaseAdapter<T>).setItems(items)
    } ?: (view.adapter as BaseAdapter<T>).setItems(emptyList())
}


@BindingAdapter("app:loading")
fun ProgressBar.loading(state: NetworkStatus<Any>?) {
    state?.let{
        this.isVisible = (it is NetworkStatus.Loading)
    }
}


@BindingAdapter(value = ["app:setImage", "app:imageSize"])
fun ImageView.setImage(url: String?, imageSize: String?) {
    url?.let { this.loadImage(it, imageSize) }
}

@BindingAdapter(value = ["app:imageUrl", "app:imageSize"])
fun ImageView.setImage2(url: String?, imageSize: String?) {
    url?.let { this.loadImage(it, imageSize) }
}

@BindingAdapter(value = ["app:setText"])
fun TextView.setText(text: String?) {
    this.text = text
}


@SuppressLint("SetTextI18n")
@BindingAdapter(value = ["app:vote_count"])
fun TextView.voteCount(text: Int?) {
    this.text = text?.format()
}

@BindingAdapter(value = ["app:setDate"])
fun TextView.setDate(text: String?) {
    text?.let {
        this.text = it
    }
}

@BindingAdapter(value = ["app:setTime"])
fun TextView.setTime(text: Int?) {
    this.text = text?.formatHourMinutes()
}


@BindingAdapter(value = ["app:setTextRate"])
fun TextView.setTextRate(rating: Double?) {
    rating?.let {
        this.text = it.toInt().toString()
    }
}

@BindingAdapter(value = ["app:setRate"])
fun RatingBar.setRate(rating: Double?) {
    rating?.let {
        this.rating = it.div(2.0f).toFloat()
    }
}


@BindingAdapter(value = ["app:manageState"])
fun <T> View.manageState(state: NetworkStatus<T>?) {
    if (state is NetworkStatus.Error)
        this.visibility = VISIBLE
    else
        this.visibility = INVISIBLE
}

