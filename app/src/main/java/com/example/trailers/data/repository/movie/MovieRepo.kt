package com.example.trailers.data.repository.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.trailers.data.model.movie.similar.Result
import com.example.trailers.data.network.ApiService
import com.example.trailers.utils.Constant
import com.example.trailers.utils.SafeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepo @Inject constructor(
    private val api: ApiService,
) : SafeApiCall {

    suspend fun getMoviesDetails(id: Int?) = safeApiCall(api.getMoviesDetails(id)).flowOn(Dispatchers.IO)
    suspend fun getActors(id: Int?) = safeApiCall(api.getActors(id)).flowOn(Dispatchers.IO)
    suspend fun getSimilar(id: Int?) = safeApiCall(api.getSimilar(id, page = 1)).flowOn(Dispatchers.IO)
    suspend fun getMovieTrailer(id: Int?) = safeApiCall(api.getMovieTrailer(id = id)).flowOn(Dispatchers.IO)
    suspend fun getMovieOfActor(person_id: Int?) = safeApiCall(api.getMovieOfActor(person_id = person_id)).flowOn(Dispatchers.IO)

    fun getAllSimilar(id: Int): Flow<PagingData<Result>> =
        Pager(config = PagingConfig(pageSize = Constant.DEFAULT_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = { MoviePagingData(api = api, id = id) }
        ).flow.flowOn(Dispatchers.IO)

}