package com.example.trailers.data.repository.search

import androidx.paging.PagingData
import com.example.trailers.data.model.movie.search.Result
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getMovieSearch(query: String?): Flow<PagingData<Result>>
}