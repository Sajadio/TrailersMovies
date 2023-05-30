package com.sajjadio.trailers.data.repository.movie

import com.sajjadio.trailers.data.network.ApiService
import com.sajjadio.trailers.utils.SafeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class  MovieRepoImpl @Inject constructor(
    private val api: ApiService,
) : MovieRepository, SafeApiCall {

    override suspend fun getMoviesDetails(id: Int?) =
        safeApiCall{api.getMoviesDetails(id)}.flowOn(Dispatchers.IO)

    override suspend fun getActors(id: Int?) = safeApiCall{api.getActors(id)}.flowOn(Dispatchers.IO)

    override suspend fun getSimilar(id: Int?) =
        safeApiCall{api.getSimilar(id, page = 1)}.flowOn(Dispatchers.IO)

    override suspend fun getMovieTrailer(id: Int?) =
        safeApiCall{api.getMovieTrailer(id = id)}.flowOn(Dispatchers.IO)

   override  suspend fun getMovieOfActor(person_id: Int?) =
        safeApiCall{api.getMovieOfActor(person_id = person_id)}.flowOn(Dispatchers.IO)

}