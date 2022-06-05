package com.example.trailers.data.repository.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.trailers.data.model.movie.similar.Result
import com.example.trailers.data.network.ApiService
import com.example.trailers.utils.Constant

class MoviePagingData(
    private val api: ApiService,
    private val id: Int,
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
            val response = api.getSimilar(id = id, page = pageNumber)
            val data = if (response.isSuccessful) response.body()?.results else null
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