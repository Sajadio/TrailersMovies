package com.sajjadio.trailers.domain.repository

import androidx.paging.PagingData
import com.sajjadio.trailers.data.model.genre.Genre
import com.sajjadio.trailers.data.model.movie.actorsmovie.ActorsMovie
import com.sajjadio.trailers.data.model.movie.common.Common
import com.sajjadio.trailers.data.model.movie.common.CommonResult
import com.sajjadio.trailers.data.model.movie.genremovie.MovieResult
import com.sajjadio.trailers.data.model.movie.id.IDMovie
import com.sajjadio.trailers.data.model.movie.search.SearchResult
import com.sajjadio.trailers.data.model.movie.similar.Similar
import com.sajjadio.trailers.data.model.movie.similar.SimilarResultDto
import com.sajjadio.trailers.data.model.movie.trend.TrendMovie
import com.sajjadio.trailers.data.model.movie.video.VideoMovie
import com.sajjadio.trailers.domain.model.Cast
import com.sajjadio.trailers.domain.model.SimilarResult
import com.sajjadio.trailers.utils.NetworkStatus
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getTrendMovie(): Flow<NetworkStatus<TrendMovie?>>
    suspend fun getMoviePopular(): Flow<NetworkStatus<Common?>>
    suspend fun getMovieTopRated(): Flow<NetworkStatus<Common?>>
    suspend fun getUpComingMovie(): Flow<NetworkStatus<Common?>>

    suspend fun getMovieDetails(id: Int?): Flow<NetworkStatus<IDMovie?>>
    suspend fun getActors(id: Int?): Flow<NetworkStatus<List<Cast>?>>
    suspend fun getSimilar(id: Int?,page:Int): Flow<NetworkStatus<List<SimilarResult>?>>
    suspend fun getMovieTrailer(id: Int?): Flow<NetworkStatus<VideoMovie?>>

    suspend fun getMovieOfActor(person_id: Int?): Flow<NetworkStatus<ActorsMovie?>>
    fun getMovieSearch(query: String?): Flow<PagingData<SearchResult>>

    fun getGenreList(genreId: String): Flow<PagingData<MovieResult>>
    suspend fun getGenresMovie(): Flow<NetworkStatus<Genre?>>

    fun listSimilarOfMovie(id: Int): Flow<PagingData<SimilarResultDto>>

    fun getPopularMoviePaging(): Flow<PagingData<CommonResult>>
    fun getTopRatedMoviePaging(): Flow<PagingData<CommonResult>>
    fun getUpComingMoviePaging(): Flow<PagingData<CommonResult>>

}