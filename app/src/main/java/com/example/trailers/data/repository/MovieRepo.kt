package com.example.trailers.data.repository

import com.example.trailers.data.network.ApiService
import com.example.trailers.utils.SafeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepo @Inject constructor(
    private val api: ApiService,
) : SafeApiCall {

    fun getMoviesDetails(id: Int?) = safeApiCall { api.getMoviesDetails(id) }
    fun getActors(id: Int?) = safeApiCall { api.getActors(id) }
    fun getSimilar(id: Int?) = safeApiCall { api.getSimilar(id) }
}