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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.appbar.MaterialToolbar
import com.sajjadio.trailers.domain.model.Genre
import com.sajjadio.trailers.domain.utils.Resource
import com.sajjadio.trailers.ui.base.BaseAdapter
import com.sajjadio.trailers.ui.base.BaseInteractListener
import com.sajjadio.trailers.ui.movie_details.adapter.MovieDetailsInteractListener
import com.sajjadio.trailers.ui.person_details.adapter.PersonDetailsInteractListener


@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    (view.adapter as BaseAdapter<T>).setItems(items ?: emptyList())
}

@BindingAdapter(value = ["app:customWidth"])
fun setWidth(view: View, widthInDp: Int?) {
    widthInDp?.let {
        val layoutParams = view.layoutParams
        val widthInPx = widthInDp.convertDpToPx(view.context)
        layoutParams.width = widthInPx
        view.layoutParams = layoutParams
    }
}

@BindingAdapter(value = ["app:customHeight"])
fun setHeight(view: View, heightInDp: Int?) {
    heightInDp?.let {
        val layoutParams = view.layoutParams
        val widthInPx = heightInDp.convertDpToPx(view.context)
        layoutParams.height = widthInPx
        view.layoutParams = layoutParams
    }
}

@BindingAdapter(value = ["app:imageUrl", "app:imageSize"])
fun setImageWithSize(imageView: ImageView, url: String?, imageSize: String?) {
    url?.let {
        imageView.loadImageWithSize(it, imageSize)
    }
}

@BindingAdapter(value = ["app:genreImageUrl"])
fun setImage(imageView: ImageView, url: String?) {
    url?.let {
        imageView.loadImage(it)
    }
}


@BindingAdapter(value = ["app:genres"])
fun setGenres(textView: TextView, genres: List<Genre>?) {
    var genre = ""
    genres?.forEachIndexed { index, it ->
        genre += if (index != genre.lastIndex) {
            "${it.name} | "
        } else {
            it.name
        }
    }
    textView.text = genre
}

@BindingAdapter(value = ["app:list"])
fun formatListString(textView: TextView, names: List<String>?) {
    var name = ""
    names?.forEachIndexed { index, it ->
        name += if (index != names.lastIndex) {
            "$it | "
        } else {
            it
        }
    }
    textView.text = name

}

@BindingAdapter(value = ["app:shimmer"])
fun <T : Any> manageShimmerFrameLayout(shimmer: ShimmerFrameLayout, data: PagingData<T>?) {
    if (data != null) {
        shimmer.stopShimmer()
    } else {
        shimmer.startShimmer()
    }
}

@BindingAdapter("app:date")
fun setDateText(textView: TextView, date: String?) {
    if (date != null) {
        val formattedDate = date.replace("-", ".")
        textView.text = formattedDate
    }
}

@BindingAdapter(
    value = [
        "app:largeImage",
        "app:largeImageSize",
        "app:onClickDownloadImage"
    ]
)
fun openLargeImage(
    imageView: ImageView,
    imageUrl: String,
    imageSize: String,
    listener: BaseInteractListener
) {
    imageView.setOnClickListener { view ->
        val context = view.context
        context.openLargeImageInDialog(imageUrl, imageSize) {
            when (listener) {
                is PersonDetailsInteractListener -> {
                    listener.onClickDownloadImage(it)
                }

                is MovieDetailsInteractListener -> {
                    listener.onClickDownloadImage(it)
                }
            }
        }
    }
}

@BindingAdapter(value = ["app:visibleNavIcon"])
fun setVisibilityIconOfToolBar(view: MaterialToolbar, visible: Boolean) {
    if (!visible) {
        view.navigationIcon = null
    }
}
