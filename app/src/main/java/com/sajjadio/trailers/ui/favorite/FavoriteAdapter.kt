package com.sajjadio.trailers.ui.favorite

import com.sajjadio.trailers.R
import com.sajjadio.trailers.domain.model.MovieDetails
import com.sajjadio.trailers.ui.base.BaseAdapter
import com.sajjadio.trailers.ui.base.BaseInteractListener

class FavoriteAdapter(
    items:List<MovieDetails>,
    listener: FavoriteInteractListener,
):BaseAdapter<MovieDetails>(items,listener) {
    override var layoutId = R.layout.layout_favorite_card
}
interface FavoriteInteractListener: BaseInteractListener{
    fun onClickFavoriteButton(movieId:Int)
    fun onClickWatchButton(id:Int)
}