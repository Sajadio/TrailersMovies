package com.example.movie.data.repository.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movie.data.model.search.Result
import com.example.movie.data.network.ApiService
import com.example.movie.utils.Constant
import retrofit2.HttpException

class SearchPagingSource(
    private val api: ApiService,
    private val query: String,
) : PagingSource<Int, Result>() {

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val pageNumber = params.key ?: Constant.INITIAL_PAGE
        return try {
            val response = api.getMultiSearch(query = query, page = pageNumber)
            val data = response.results
            LoadResult.Page(
                data = data ?: emptyList(),
                prevKey = if (pageNumber == Constant.INITIAL_PAGE) null else pageNumber.minus(1),
                nextKey = if (data?.isEmpty() == true) null else pageNumber.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}