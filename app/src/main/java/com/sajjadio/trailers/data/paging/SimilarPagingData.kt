package com.sajjadio.trailers.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sajjadio.trailers.data.model.movie.similar.SimilarResultDto
import com.sajjadio.trailers.data.remote.MovieApiService
import com.sajjadio.trailers.domain.mapper.SimilarMapper
import com.sajjadio.trailers.domain.model.SimilarResult
import com.sajjadio.trailers.utils.Constant

class SimilarPagingData(
    private val api: MovieApiService,
    private val id: Int,
    private val similarMapper: SimilarMapper
) : PagingSource<Int, SimilarResult>() {

    override fun getRefreshKey(state: PagingState<Int, SimilarResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SimilarResult> {
        return try {
            val pageNumber = params.key ?: Constant.DEFAULT_PAGE_INDEX
            val response = api.getSimilar(id = id, page = pageNumber)
            val data = response.takeIf { it.isSuccessful }?.let {
                response.body()?.results?.let {
                    similarMapper.mapTo(it)
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