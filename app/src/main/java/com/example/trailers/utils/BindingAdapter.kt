package com.example.trailers.utils

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.trailers.R
import com.example.trailers.ui.base.adapter.BaseAdapter
import com.example.trailers.ui.fragment.home.adapter.MultiTypeViewAdapter

//@BindingAdapter("setAdapter")
//fun setAdapter(
//    recyclerView: RecyclerView,
//    adapter: BaseAdapter<ViewDataBinding, ParentListAdapter>?,
//) {
//    adapter?.let {
//        recyclerView.adapter = it
//    }
//}
//
//
//@Suppress("UNCHECKED_CAST")
//@BindingAdapter("submitList")
//fun submitList(recyclerView: RecyclerView, list: List<ParentListAdapter>?) {
//    val adapter = recyclerView.adapter as BaseAdapter<ViewDataBinding, ParentListAdapter>?
//    list?.let {
//        adapter?.update(list)
//    }
//}

//@Suppress("UNCHECKED_CAST")
//@BindingAdapter("app:submitList")
//fun submitList(recyclerView: RecyclerView, list: List<ParentListAdapter>?) {
//    val adapter = recyclerView.adapter as BaseAdapter<ViewDataBinding, ParentListAdapter>
//    list?.let {
//        adapter.differ.submitList(it)
//    }
//}

@BindingAdapter("app:loading")
fun ProgressBar.loading(state: List<MultiViewTypeItem<NetworkStatus<Any>>>?) {
    state?.map {
        this.isVisible = (it.item is NetworkStatus.Loading)
    }
}

@BindingAdapter("app:stateManage")
fun RecyclerView.stateManage(state: List<MultiViewTypeItem<NetworkStatus<Any>>>?) {
    state?.map {
        this.isVisible = (it.item !is NetworkStatus.Error)
    }
}


@BindingAdapter("app:mError")
fun TextView.mError(state: List<MultiViewTypeItem<NetworkStatus<Any>>>?) {
    state?.map {
        if (it.item is NetworkStatus.Error) {
            this.isVisible = true
            when {
                it.item.isNetworkError -> Log.d("sajjadio",
                    "checkConnection: ${this.resources.getString(R.string.noConnection)}")
                it.item.errorCode is Int -> Log.d("sajjadio", "mError: ${it.item.errorCode.toString()}")
                else -> Log.d("sajjadio", "mError: ${it.item.errorBody?.source()?.buffer.toString()}")
            }
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
        this.text = it.div(2.0f).toString()
    }
}

@BindingAdapter(value = ["app:setRate"])
fun RatingBar.setRate(rating: Double?) {
    rating?.let {
        this.rating = it.div(2.0f).toFloat()
    }
}

//
//@BindingAdapter(value = ["app:chips"])
//fun ChipGroup.setChips(chipText: List<String>?) {
//
//    val chip = LayoutInflater.from(context).inflate(R.layout.layout_chips, this, false) as Chip
//    chipText?.forEach { chip.text = it }
//    this.addView(chip)
//}


@BindingAdapter(value = ["app:manageState"])
fun <T> View.manageState(state: NetworkStatus<T>?) {
    if (state is NetworkStatus.Error)
        this.visibility = VISIBLE
    else
        this.visibility = INVISIBLE
}
