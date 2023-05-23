package com.sajjadio.trailers.data.repository.search

import androidx.paging.PagingData
import com.sajjadio.trailers.data.model.movie.search.Result
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getMovieSearch(query: String?): Flow<PagingData<Result>>
}