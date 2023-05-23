package com.sajjadio.trailers.data.repository.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sajjadio.trailers.data.model.movie.search.Result
import com.sajjadio.trailers.data.network.ApiService
import com.sajjadio.trailers.utils.Constant
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepoImpl @Inject constructor(
    private val api: ApiService,
) : SearchRepository {

    override fun getMovieSearch(query: String?): Flow<PagingData<Result>> =
        Pager(config = PagingConfig(pageSize = Constant.DEFAULT_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = { SearchPagingSource(api = api, query = query) }
        ).flow
}