package com.sajjadio.trailers.data.repository.movie

import com.sajjadio.trailers.data.model.movie.actors.Actors
import com.sajjadio.trailers.data.model.movie.actorsmovie.ActorsMovie
import com.sajjadio.trailers.data.model.movie.id.IDMovie
import com.sajjadio.trailers.data.model.movie.similar.Similar
import com.sajjadio.trailers.data.model.movie.video.VideoMovie
import com.sajjadio.trailers.utils.NetworkStatus
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMoviesDetails(id: Int?): Flow<NetworkStatus<IDMovie?>>
    suspend fun getActors(id: Int?): Flow<NetworkStatus<Actors?>>
    suspend fun getSimilar(id: Int?): Flow<NetworkStatus<Similar?>>
    suspend fun getMovieTrailer(id: Int?): Flow<NetworkStatus<VideoMovie?>>
    suspend fun getMovieOfActor(person_id: Int?): Flow<NetworkStatus<ActorsMovie?>>
}