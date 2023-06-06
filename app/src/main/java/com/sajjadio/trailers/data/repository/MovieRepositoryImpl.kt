package com.sajjadio.trailers.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sajjadio.trailers.data.model.movie.common.CommonResult
import com.sajjadio.trailers.data.model.movie.genremovie.MovieResult
import com.sajjadio.trailers.data.model.movie.search.SearchResult
import com.sajjadio.trailers.data.model.movie.similar.SimilarResultDto
import com.sajjadio.trailers.data.remote.MovieApiService
import com.sajjadio.trailers.data.paging.ComingPagingSource
import com.sajjadio.trailers.data.paging.GenresPagingSource
import com.sajjadio.trailers.data.paging.PopularPagingSource
import com.sajjadio.trailers.data.paging.RatedPagingSource
import com.sajjadio.trailers.data.paging.SimilarPagingData
import com.sajjadio.trailers.data.paging.SearchPagingSource
import com.sajjadio.trailers.domain.mapper.ActorMapper
import com.sajjadio.trailers.domain.mapper.MovieDetailsMapper
import com.sajjadio.trailers.domain.mapper.SimilarMapper
import com.sajjadio.trailers.domain.model.Cast
import com.sajjadio.trailers.domain.model.MovieDetails
import com.sajjadio.trailers.domain.model.SimilarResult
import com.sajjadio.trailers.domain.repository.MovieRepository
import com.sajjadio.trailers.utils.Constant
import com.sajjadio.trailers.utils.NetworkStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApiService,
    private val actorMapper: ActorMapper,
    private val similarMapper: SimilarMapper,
    private val movieDetailsMapper: MovieDetailsMapper
) : MovieRepository {

    override suspend fun getTrendMovie() =
        wrapWithFlow { movieApi.getTrending() }.flowOn(Dispatchers.IO)

    override suspend fun getMoviePopular() =
        wrapWithFlow { movieApi.getPopularMovie() }.flowOn(Dispatchers.IO)

    override suspend fun getMovieTopRated() =
        wrapWithFlow { movieApi.getTopRatedMovie() }.flowOn(Dispatchers.IO)

    override suspend fun getUpComingMovie() =
        wrapWithFlow { movieApi.getUpComingMovie() }.flowOn(Dispatchers.IO)

    override fun listSimilarOfMovie(id: Int): Flow<PagingData<SimilarResultDto>> =
        Pager(config = PagingConfig(pageSize = Constant.DEFAULT_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = { SimilarPagingData(api = movieApi, id = id) }
        ).flow.flowOn(Dispatchers.IO)

    override fun getMovieSearch(query: String?): Flow<PagingData<SearchResult>> {
        return Pager(config = PagingConfig(
            pageSize = Constant.DEFAULT_PAGE_SIZE,
            prefetchDistance = 2
        ),
            pagingSourceFactory = {
                SearchPagingSource(api = movieApi, query = query)
            }
        ).flow
    }

    override suspend fun getMovieDetails(id: Int?): Flow<NetworkStatus<MovieDetails>> {
        return wrapper({ movieApi.getMovieDetails(id) }) { movieDetailsDto ->
            movieDetailsMapper.mapTo(movieDetailsDto)
        }
    }

    override suspend fun getActors(id: Int?): Flow<NetworkStatus<List<Cast>?>> {
        return wrapper({ movieApi.getActors(id) }) { actors ->
            actors.cast?.let { castDto -> actorMapper.mapTo(castDto) }
        }
    }

    override suspend fun getSimilar(
        id: Int?,
        page: Int
    ): Flow<NetworkStatus<List<SimilarResult>?>> {
        return wrapper({ movieApi.getSimilar(id, page) }) { similar ->
            similar.results?.let { similarResultDto -> similarMapper.mapTo(similarResultDto) }
        }
    }

    override suspend fun getMovieTrailer(id: Int?) =
        wrapWithFlow { movieApi.getMovieTrailer(id = id) }.flowOn(Dispatchers.IO)

    override suspend fun getMovieOfActor(person_id: Int?) =
        wrapWithFlow { movieApi.getMoviesOfActor(person_id = person_id) }.flowOn(Dispatchers.IO)

    override fun getPopularMoviePaging(): Flow<PagingData<CommonResult>> =
        Pager(config = PagingConfig(pageSize = Constant.DEFAULT_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = {
                PopularPagingSource(api = movieApi)
            }
        ).flow.flowOn(Dispatchers.IO)

    override fun getTopRatedMoviePaging(): Flow<PagingData<CommonResult>> =
        Pager(config = PagingConfig(pageSize = Constant.DEFAULT_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = {
                RatedPagingSource(api = movieApi)
            }
        ).flow.flowOn(Dispatchers.IO)

    override fun getUpComingMoviePaging(): Flow<PagingData<CommonResult>> =
        Pager(config = PagingConfig(pageSize = Constant.DEFAULT_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = {
                ComingPagingSource(api = movieApi)
            }
        ).flow.flowOn(Dispatchers.IO)

    override fun getGenreList(genreId: String): Flow<PagingData<MovieResult>> =
        Pager(config = PagingConfig(pageSize = Constant.DEFAULT_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = {
                GenresPagingSource(genreId = genreId, api = movieApi)
            }
        ).flow.flowOn(Dispatchers.IO)

    override suspend fun getGenresMovie() =
        wrapWithFlow { movieApi.getGenresMovie() }.flowOn(Dispatchers.IO)

    private fun <I, O> wrapper(
        function: suspend () -> Response<I>,
        mapper: (I) -> O
    ): Flow<NetworkStatus<O>> {
        return flow {
            try {
                emit(NetworkStatus.Loading)
                val response = function()
                if (response.isSuccessful) {
                    emit(NetworkStatus.Success(response.body()?.let { mapper(it) }))
                } else {
                    emit(NetworkStatus.Error(response.message()))
                }
            } catch (e: Exception) {
                emit(NetworkStatus.Error(e.message.toString()))
            }
        }
    }

    private fun <T> wrapWithFlow(function: suspend () -> Response<T>): Flow<NetworkStatus<T>> {
        return flow {
            try {
                emit(NetworkStatus.Loading)
                emit(checkIsSuccessfulResponse(function.invoke()))
            } catch (e: Exception) {
                emit(NetworkStatus.Error(e.message.toString()))
            }
        }
    }

    private fun <T> checkIsSuccessfulResponse(response: Response<T>): NetworkStatus<T> {
        return if (response.isSuccessful)
            NetworkStatus.Success(response.body())
        else
            NetworkStatus.Error(response.message())
    }
}