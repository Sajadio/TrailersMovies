package com.sajjadio.trailers.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sajjadio.trailers.data.dataSource.local.dao.MovieDao
import com.sajjadio.trailers.data.dataSource.mapper.mapToPersonOfMovie
import com.sajjadio.trailers.data.dataSource.mapper.mapToCommonDomain
import com.sajjadio.trailers.data.dataSource.mapper.mapToCommonResultDomain
import com.sajjadio.trailers.data.dataSource.mapper.mapToGenresDomain
import com.sajjadio.trailers.data.dataSource.mapper.mapToImagesDomain
import com.sajjadio.trailers.data.dataSource.mapper.mapToMovieDetails
import com.sajjadio.trailers.data.dataSource.mapper.mapToPerson
import com.sajjadio.trailers.data.dataSource.mapper.mapToTrendMove
import com.sajjadio.trailers.data.dataSource.remote.MovieApiService
import com.sajjadio.trailers.data.paging.ComingPagingSource
import com.sajjadio.trailers.data.paging.MoviesOfGenresPagingSource
import com.sajjadio.trailers.data.paging.PopularPagingSource
import com.sajjadio.trailers.data.paging.RatedPagingSource
import com.sajjadio.trailers.data.paging.SimilarPagingData
import com.sajjadio.trailers.data.paging.SearchPagingSource
import com.sajjadio.trailers.domain.model.Cast
import com.sajjadio.trailers.domain.model.Common
import com.sajjadio.trailers.domain.model.MovieDetails
import com.sajjadio.trailers.domain.model.CommonResult
import com.sajjadio.trailers.domain.model.GenresOfMovie
import com.sajjadio.trailers.domain.model.Person
import com.sajjadio.trailers.domain.model.Image
import com.sajjadio.trailers.domain.model.TrendMovie
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
    private val dao: MovieDao
) : MovieRepository {

    override suspend fun addMovie(movieDetails: MovieDetails): Long {
        return dao.addMovie(movieDetails)
    }

    override fun getAllSavedMovies(): Flow<List<MovieDetails>> {
        return dao.getAllSavedMovies()
    }

    override suspend fun deleteAllMovies() {
        return dao.deleteAllMovies()
    }

    override suspend fun checkISMovieSavedByTitle(title: String): Boolean {
        return dao.checkISMovieSavedByTitle(title)
    }

    override suspend fun deleteMovie(movieDetails: MovieDetails) {
        return dao.deleteMovie(movieDetails)
    }

    override suspend fun getTrendMovie(): Flow<NetworkStatus<TrendMovie>> {
        return wrapper({ movieApi.getTrending() }) { trendMovie ->
            mapToTrendMove(trendMovie)
        }
    }

    override suspend fun getMoviePopular(): Flow<NetworkStatus<Common>> {
        return wrapper({ movieApi.getPopularMovie() }) {
            mapToCommonDomain(it)
        }
    }

    override suspend fun getMovieTopRated(): Flow<NetworkStatus<Common>> {
        return wrapper({ movieApi.getTopRatedMovie() }) {
            mapToCommonDomain(it)
        }
    }

    override suspend fun getUpComingMovie(): Flow<NetworkStatus<Common>> {
        return wrapper({ movieApi.getUpComingMovie() }) {
            mapToCommonDomain(it)
        }
    }

    override fun getSimilarOfMovie(id: Int): Flow<PagingData<CommonResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constant.DEFAULT_PAGE_SIZE,
                prefetchDistance = Constant.PREFETCH_DISTANCE
            ), pagingSourceFactory = {
                SimilarPagingData(
                    api = movieApi,
                    id = id,
                )
            }
        ).flow.flowOn(Dispatchers.IO)
    }

    override fun getMovieSearch(query: String?): Flow<PagingData<CommonResult>> {
        return Pager(config = PagingConfig(
            pageSize = Constant.DEFAULT_PAGE_SIZE,
            prefetchDistance = Constant.PREFETCH_DISTANCE
        ), pagingSourceFactory = {
            SearchPagingSource(api = movieApi, query = query)
        }
        ).flow.flowOn(Dispatchers.IO)
    }

    override suspend fun getMovieDetails(id: Int?): Flow<NetworkStatus<MovieDetails>> {
        return wrapper({ movieApi.getMovieDetails(id) }) { movieDetailsDto ->
            mapToMovieDetails(movieDetailsDto)
        }
    }

    override suspend fun getImagesOfMovieById(movieId: Int?): Flow<NetworkStatus<List<Image>?>> {
        return wrapper({ movieApi.getImagesOfMovieById(movieId) }) { imageDto ->
            mapToImagesDomain(imageDto.images)
        }
    }

    override suspend fun getImagesOfPersonById(personId: Int?): Flow<NetworkStatus<List<Image>?>> {
        return wrapper({ movieApi.getImagesOfPersonById(personId) }) { imageDto ->
            mapToImagesDomain(imageDto.profileDto)
        }
    }

    override suspend fun getMoviesOfPersonById(personId: Int?): Flow<NetworkStatus<List<CommonResult>>> {
        return wrapper({ movieApi.getMoviesOfPersonById(personId) }) { similar ->
            mapToCommonResultDomain(similar.cast)
        }
    }

    override fun getPersonOfMovieById(id: Int): Flow<NetworkStatus<List<Cast>?>> {
        return wrapper({ movieApi.getPersonOfMovieById(id) }) { actors ->
            actors.cast?.let { castDto -> mapToPersonOfMovie(castDto) }
        }
    }

    override fun getPersonById(personId: Int?): Flow<NetworkStatus<Person>> {
        return wrapper({ movieApi.getPersonById(personId) }) {
            mapToPerson(it)
        }
    }

    override suspend fun getSimilar(
        id: Int?,
        page: Int
    ): Flow<NetworkStatus<List<CommonResult>?>> {
        return wrapper({ movieApi.getSimilar(id, page) }) { similar ->
            mapToCommonResultDomain(similar.results)
        }
    }

    override suspend fun getMovieTrailer(id: Int?) =
        wrapWithFlow { movieApi.getMovieTrailer(id = id) }.flowOn(Dispatchers.IO)

    override suspend fun getMovieOfActor(person_id: Int?) =
        wrapWithFlow { movieApi.getMoviesOfActor(personId = person_id) }.flowOn(Dispatchers.IO)

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

    override fun getMoviesOfGenreById(genreId: Int): Flow<PagingData<CommonResult>> =
        Pager(config = PagingConfig(pageSize = Constant.DEFAULT_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = {
                MoviesOfGenresPagingSource(genreId = genreId, api = movieApi)
            }
        ).flow.flowOn(Dispatchers.IO)

    override suspend fun getGenresMovie(): Flow<NetworkStatus<List<GenresOfMovie>>> {
        return wrapper({
            movieApi.getGenresMovie()
        }) {
            mapToGenresDomain(it.genres)
        }
    }

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