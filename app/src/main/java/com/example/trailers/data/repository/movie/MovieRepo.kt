package com.example.trailers.data.repository.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.trailers.data.model.movie.similar.Result
import com.example.trailers.data.network.ApiService
import com.example.trailers.utils.SafeApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepo @Inject constructor(
    private val api: ApiService,
) : SafeApiCall {

    suspend fun getMoviesDetails(id: Int?) = safeApiCall(api.getMoviesDetails(id))
    suspend fun getActors(id: Int?) = safeApiCall(api.getActors(id))
    suspend fun getSimilar(id: Int?) = safeApiCall(api.getSimilar(id, page = 1))
    suspend fun getMovieTrailer(id: Int?) = safeApiCall(api.getMovieTrailer(id = id))
    suspend fun getMovieOfActor(person_id: Int?) = safeApiCall(api.getMovieOfActor(person_id = person_id))

    fun getAllSimilar(id: Int): Flow<PagingData<Result>> =
        Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = { MoviePagingData(api = api, id = id) }
        ).flow

}