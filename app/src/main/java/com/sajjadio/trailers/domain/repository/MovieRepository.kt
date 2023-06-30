package com.sajjadio.trailers.domain.repository

import androidx.paging.PagingData
import com.sajjadio.trailers.data.model.genre.Genre
import com.sajjadio.trailers.data.model.movie.actorsmovie.ActorsMovie
import com.sajjadio.trailers.data.model.movie.common.CommonResultDto
import com.sajjadio.trailers.data.model.movie.genremovie.MovieResult
import com.sajjadio.trailers.data.model.movie.search.SearchResult
import com.sajjadio.trailers.data.model.movie.trend.TrendMovieDto
import com.sajjadio.trailers.data.model.movie.video.VideoMovie
import com.sajjadio.trailers.domain.model.Cast
import com.sajjadio.trailers.domain.model.Common
import com.sajjadio.trailers.domain.model.MovieDetails
import com.sajjadio.trailers.domain.model.CommonResult
import com.sajjadio.trailers.domain.model.TrendMovie
import com.sajjadio.trailers.utils.NetworkStatus
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getTrendMovie(): Flow<NetworkStatus<TrendMovie>>
    suspend fun getMoviePopular(): Flow<NetworkStatus<Common?>>
    suspend fun getMovieTopRated(): Flow<NetworkStatus<Common?>>
    suspend fun getUpComingMovie(): Flow<NetworkStatus<Common?>>

    suspend fun getMovieDetails(id: Int?): Flow<NetworkStatus<MovieDetails>>
    suspend fun getActors(id: Int?): Flow<NetworkStatus<List<Cast>?>>
    suspend fun getSimilar(id: Int?,page:Int): Flow<NetworkStatus<List<CommonResult>?>>
    suspend fun getMovieTrailer(id: Int?): Flow<NetworkStatus<VideoMovie?>>

    suspend fun getMovieOfActor(person_id: Int?): Flow<NetworkStatus<ActorsMovie?>>
    fun getMovieSearch(query: String?): Flow<PagingData<SearchResult>>

    fun getGenreList(genreId: String): Flow<PagingData<MovieResult>>
    suspend fun getGenresMovie(): Flow<NetworkStatus<Genre?>>

    fun getSimilarOfMovie(id: Int): Flow<PagingData<CommonResult>>

    fun getPopularMoviePaging(): Flow<PagingData<CommonResultDto>>
    fun getTopRatedMoviePaging(): Flow<PagingData<CommonResultDto>>
    fun getUpComingMoviePaging(): Flow<PagingData<CommonResultDto>>

}