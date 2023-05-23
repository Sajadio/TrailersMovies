package com.sajjadio.trailers.data.repository.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sajjadio.trailers.data.model.movie.search.Result
import com.sajjadio.trailers.data.network.ApiService
import com.sajjadio.trailers.utils.Constant

class SearchPagingSource(
    private val api: ApiService,
    private val query: String?,
) : PagingSource<Int, Result>() {

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val pageNumber = params.key ?: Constant.DEFAULT_PAGE_INDEX
        return try {
            val response = api.getSearchMovie(query = query, page = pageNumber)
            val data = response.results
            LoadResult.Page(
                data = data ?: emptyList(),
                prevKey = if (pageNumber == Constant.DEFAULT_PAGE_INDEX) null else pageNumber.minus(1),
                nextKey = if (data?.isEmpty() == true) null else pageNumber.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}