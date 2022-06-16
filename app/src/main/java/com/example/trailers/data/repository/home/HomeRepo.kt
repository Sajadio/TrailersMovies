package com.example.trailers.data.repository.home


import com.example.trailers.data.network.ApiService
import com.example.trailers.utils.SafeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class HomeRepo @Inject constructor(
    private val api: ApiService,
) : SafeApiCall {

    suspend fun getTrendMovie() = safeApiCall(api.getTrending()).flowOn(Dispatchers.IO)

    suspend fun getMoviePopular() = safeApiCall(api.getPopularMovie()).flowOn(Dispatchers.IO)

    suspend fun getMovieTopRated() = safeApiCall(api.getTopRatedMovie()).flowOn(Dispatchers.IO)

    suspend fun getUpComingMovie() = safeApiCall(api.getUpComingMovie()).flowOn(Dispatchers.IO)

}