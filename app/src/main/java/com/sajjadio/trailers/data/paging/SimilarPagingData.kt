package com.sajjadio.trailers.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sajjadio.trailers.data.mapper.mapToCommonResult
import com.sajjadio.trailers.data.remote.MovieApiService
import com.sajjadio.trailers.domain.model.CommonResult
import com.sajjadio.trailers.utils.Constant

class SimilarPagingData(
    private val api: MovieApiService,
    private val id: Int,
) : PagingSource<Int, CommonResult>() {

    override fun getRefreshKey(state: PagingState<Int, CommonResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CommonResult> {
        return try {
            val pageNumber = params.key ?: Constant.DEFAULT_PAGE_INDEX
            val response = api.getSimilar(id = id, page = pageNumber)
            val data = response.takeIf { it.isSuccessful }?.let {
                response.body()?.results?.let {
                    mapToCommonResult(it)
                }
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