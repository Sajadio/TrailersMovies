package com.example.movie.data.repository

import com.example.movie.data.model.movie.popular.PopularMovie
import com.example.movie.data.model.tv.popular.PopularTV
import com.example.movie.data.network.ApiService
import com.example.movie.utils.NetworkStatus
import com.example.movie.utils.ParentListAdapter
import com.example.movie.utils.SafeApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MDBRepo @Inject constructor(
    private val api: ApiService,
) : SafeApiCall {

    fun getTrending() = safeApiCall { api.getTrending() }
    fun getMoviePopular() = safeApiCall { api.getMoviePopular() }
    fun getTVPopular() = safeApiCall { api.getTVPopular() }

//    fun filterResultPopular(filter:String): Flow<NetworkStatus<PopularTV>> {
//        if (filter == "Movie")
//            return getMoviePopular()
//        else
//            return getTVPopular()
//    }
}