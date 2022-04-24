package com.example.movie.utils

import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.ui.base.adapter.BaseAdapter
import com.example.movie.ui.base.nestedrv.HomeAdapter
import com.example.movie.ui.fragment.home.adapter.TrendAdapter
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.transform.ScaleTransformer

@BindingAdapter("setAdapter")
fun setHomeAdapter(
    recyclerView: RecyclerView,
    adapter: HomeAdapter?
) {
    adapter?.let {
        recyclerView.adapter = it
        recyclerView.setHasFixedSize(true)
    }
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun submitHomeList(recyclerView: RecyclerView, list: List<ListHomeAdapterItem<Any>>?) {
    val adapter = recyclerView.adapter as HomeAdapter
//    adapter?.updateData(list ?: listOf())
}













//@BindingAdapter(value = ["app:setPosterCategory"])
//fun ImageView.setImage(url: Int?) {
//    url?.let { this.loadImage(it) }
//}
//
//@BindingAdapter(value = ["app:setTitleMS"])
//fun TextView.setText(text: String?) {
//    this.text = text
//}
//
//@BindingAdapter(value = ["app:rating"])
//fun RatingBar.setRate(rating: Float?) {
//    rating?.let { this.rating = it }
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
//@BindingAdapter("setImage")
//fun setImage(imageView: ShapeableImageView, image: Int) {
//    Glide.with(imageView.context)
//        .load(image)
//        .into(imageView)
//}
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