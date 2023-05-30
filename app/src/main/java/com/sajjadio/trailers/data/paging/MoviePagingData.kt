package com.sajjadio.trailers.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sajjadio.trailers.data.model.movie.similar.SimilarResult
import com.sajjadio.trailers.data.remote.MovieApiService
import com.sajjadio.trailers.utils.Constant

class MoviePagingData(
    private val api: MovieApiService,
    private val id: Int,
) : PagingSource<Int, SimilarResult>() {

    override fun getRefreshKey(state: PagingState<Int, SimilarResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SimilarResult> {
        val pageNumber = params.key ?: Constant.DEFAULT_PAGE_INDEX
        return try {
            val response = api.getSimilar(id = id, page = pageNumber)
            val data = if (response.isSuccessful) response.body()?.similarResults else null
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