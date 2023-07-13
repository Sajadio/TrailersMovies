package com.sajjadio.trailers.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sajjadio.trailers.data.dataSource.mapper.mapToCommonResultDomain
import com.sajjadio.trailers.data.dataSource.model.movie.common.CommonResultDto
import com.sajjadio.trailers.data.dataSource.remote.MovieApiService
import com.sajjadio.trailers.domain.model.CommonResult
import com.sajjadio.trailers.utils.Constant

class SearchPagingSource(
    private val api: MovieApiService,
    private val query: String?,
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
            val response = api.getSearchMovie(query = query, page = pageNumber)
            val data = response.body()?.results?.let {
                mapToCommonResultDomain(it)
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