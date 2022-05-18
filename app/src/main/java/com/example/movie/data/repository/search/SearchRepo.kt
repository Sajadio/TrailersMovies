package com.example.movie.data.repository.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movie.data.model.search.Result
import com.example.movie.data.network.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepo @Inject constructor(
    private val api: ApiService,
) {

    fun getMultiSearch(query: String): Flow<PagingData<Result>> =
        Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = { SearchPagingSource(api = api, query = query) }
        ).flow

}