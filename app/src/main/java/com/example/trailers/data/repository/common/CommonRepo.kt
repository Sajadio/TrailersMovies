package com.example.trailers.data.repository.common

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.trailers.data.model.movie.common.CommonResult
import com.example.trailers.data.model.movie.genremovie.MovieResult
import com.example.trailers.data.network.ApiService
import com.example.trailers.utils.SafeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommonRepo @Inject constructor(
    private val api: ApiService,
) : SafeApiCall {


    fun getPopularMoviePaging(): Flow<PagingData<CommonResult>> =
        Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = {
                PopularPagingSource(api = api)
            }
        ).flow.flowOn(Dispatchers.IO)

    fun getTopRatedMoviePaging(): Flow<PagingData<CommonResult>> =
        Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = {
                RatedPagingSource(api = api)
            }
        ).flow.flowOn(Dispatchers.IO)

    fun getUpComingMoviePaging(): Flow<PagingData<CommonResult>> =
        Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = {
                ComingPagingSource(api = api)
            }
        ).flow.flowOn(Dispatchers.IO)

    fun getGenreList(genreId: String): Flow<PagingData<MovieResult>> =

        Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = {
                GenresPagingSource(genreId = genreId, api = api)
            }
        ).flow.flowOn(Dispatchers.IO)

    suspend fun getGenresMovie() = safeApiCall(api.getGenresMovie())

}