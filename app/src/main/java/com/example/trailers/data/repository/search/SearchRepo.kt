package com.example.trailers.data.repository.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.trailers.data.model.movie.search.Result
import com.example.trailers.data.network.ApiService
import com.example.trailers.utils.Constant
import com.example.trailers.utils.SafeApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepo @Inject constructor(
    private val api: ApiService,
) : SafeApiCall {

    fun getMovieSearch(query: String): Flow<PagingData<Result>> =
        Pager(config = PagingConfig(pageSize = Constant.DEFAULT_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = { SearchPagingSource(api = api, query = query) }
        ).flow
}