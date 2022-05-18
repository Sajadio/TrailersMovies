package com.example.trailers.utils

import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.trailers.ui.base.adapter.BaseAdapter
import com.example.trailers.ui.base.nestedrv.HomeAdapter

@BindingAdapter("app:setAdapter")
fun setAdapter(
    recyclerView: RecyclerView,
    adapter: HomeAdapter?,
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


@BindingAdapter("app:manageState")
fun <T> manageState(progressBar: ProgressBar, state: NetworkStatus<T>?) {
    when (state) {
        is NetworkStatus.Loading -> progressBar.visibility = VISIBLE
        is NetworkStatus.Success -> progressBar.visibility = INVISIBLE
        is NetworkStatus.Error -> progressBar.visibility = INVISIBLE
    }
}


@BindingAdapter(value = ["app:setImage"])
fun ImageView.setImage(url: String?) {
    url?.let { this.loadImage(it) }
}

//
//@BindingAdapter(value = ["app:setText"])
//fun TextView.setText(text: String?) {
//    this.text = text
//}


//@BindingAdapter(value = ["app:setRate"])
//fun RatingBar.setRate(rating: Double?) {
//    rating?.let { this.rating = it.toFloat() }
//}

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