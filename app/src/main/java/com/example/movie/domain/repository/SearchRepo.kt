package com.example.movie.domain.repository

import com.example.movie.domain.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepo @Inject constructor(
    private val apiService: ApiService,
) {

     suspend fun getMoviesSearch(query:String) = apiService.getMoviesSearch(query = query)

}