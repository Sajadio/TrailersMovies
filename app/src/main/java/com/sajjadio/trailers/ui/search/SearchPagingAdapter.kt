package com.sajjadio.trailers.ui.search


import com.sajjadio.trailers.R
import com.sajjadio.trailers.data.model.movie.search.SearchResult
import com.sajjadio.trailers.ui.base.BaseInteractListener
import com.sajjadio.trailers.ui.base.BasePagingAdapter

class SearchPagingAdapter(
    listener: BaseInteractListener
) : BasePagingAdapter<SearchResult>(listener) {
    override var layoutId = R.layout.layout_search_card
}