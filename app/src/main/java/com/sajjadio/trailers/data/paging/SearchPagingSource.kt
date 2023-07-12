package com.sajjadio.trailers.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sajjadio.trailers.data.model.movie.search.SearchResult
import com.sajjadio.trailers.data.remote.MovieApiService
import com.sajjadio.trailers.utils.Constant

class SearchPagingSource(
    private val api: MovieApiService,
    private val query: String?,
) : PagingSource<Int, SearchResult>() {

    override fun getRefreshKey(state: PagingState<Int, SearchResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchResult> {
        return try {
            val pageNumber = params.key ?: Constant.DEFAULT_PAGE_INDEX
            val response = api.getSearchMovie(query = query, page = pageNumber)
            val data = response.takeIf { it.isSuccessful }?.let {
                it.body()?.results
            } ?: emptyList()

            LoadResult.Page(
                data = data,
                prevKey = if (pageNumber == Constant.DEFAULT_PAGE_INDEX) null
                else pageNumber.minus(1),
                nextKey = if (data.isEmpty()) null else pageNumber.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}