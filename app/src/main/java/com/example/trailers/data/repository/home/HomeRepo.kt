package com.example.trailers.data.repository.home


import com.example.trailers.data.network.ApiService
import com.example.trailers.utils.SafeApiCall
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class HomeRepo @Inject constructor(
    private val api: ApiService,
) : SafeApiCall {

    suspend fun getTrendMovie() = safeApiCall(api.getTrending())

    suspend fun getMoviePopular() = safeApiCall(api.getPopularMovie())

    suspend fun getMovieTopRated() = safeApiCall(api.getTopRatedMovie())

    suspend fun getUpComingMovie() = safeApiCall(api.getUpComingMovie())

}