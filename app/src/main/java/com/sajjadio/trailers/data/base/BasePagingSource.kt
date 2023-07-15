package com.sajjadio.trailers.data.base

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sajjadio.trailers.utils.Constant
import retrofit2.Response


abstract class BasePagingSource<Key : Number, Value : Any, Mapper : Any> :
    PagingSource<Key, Mapper>() {

    override fun getRefreshKey(state: PagingState<Key, Mapper>): Key? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.toString()?.toInt()?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.toString()?.toInt()
                    ?.minus(1)
        } as Key?
    }

    override suspend fun load(params: LoadParams<Key>): LoadResult<Key, Mapper> {
        val pageNumber = params.key ?: Constant.DEFAULT_PAGE_INDEX as Key
        return try {
            val response = apiCall(pageNumber).body()
            val mapper = mapperResponse(response) ?: emptyList()

            LoadResult.Page(
                data = mapper,
                prevKey = if (pageNumber.toDouble() == Constant.DEFAULT_PAGE_INDEX.toDouble()) null else (pageNumber.toInt() - 1) as Key,
                nextKey = if (mapper.isEmpty()) null else (pageNumber.toInt() + 1) as Key
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    abstract suspend fun apiCall(pageNumber: Key): Response<Value>


    abstract fun mapperResponse(response: Value?): List<Mapper>?

}