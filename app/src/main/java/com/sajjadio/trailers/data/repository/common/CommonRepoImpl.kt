package com.sajjadio.trailers.data.repository.common

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sajjadio.trailers.data.model.movie.common.CommonResult
import com.sajjadio.trailers.data.network.ApiService
import com.sajjadio.trailers.utils.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CommonRepoImpl @Inject constructor(
    private val api: ApiService,
) : CommonRepository {


    override fun getPopularMoviePaging(): Flow<PagingData<CommonResult>> =
        Pager(config = PagingConfig(pageSize = Constant.DEFAULT_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = {
                PopularPagingSource(api = api)
            }
        ).flow.flowOn(Dispatchers.IO)

    override fun getTopRatedMoviePaging(): Flow<PagingData<CommonResult>> =
        Pager(config = PagingConfig(pageSize = Constant.DEFAULT_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = {
                RatedPagingSource(api = api)
            }
        ).flow.flowOn(Dispatchers.IO)

    override fun getUpComingMoviePaging(): Flow<PagingData<CommonResult>> =
        Pager(config = PagingConfig(pageSize = Constant.DEFAULT_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = {
                ComingPagingSource(api = api)
            }
        ).flow.flowOn(Dispatchers.IO)
}