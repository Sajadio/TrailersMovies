package com.sajjadio.trailers.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.sajjadio.trailers.data.dataSource.local.AppDatabase
import com.sajjadio.trailers.data.dataSource.local.dao.MovieDao
import com.sajjadio.trailers.data.dataSource.local.dao.MovieDetailsDao
import com.sajjadio.trailers.data.dataSource.mapper.mapDomainToMovieDetailsEntity
import com.sajjadio.trailers.data.dataSource.mapper.mapToPopularMovieEntity
import com.sajjadio.trailers.data.dataSource.mapper.mapToTopRatedMovieEntity
import com.sajjadio.trailers.data.dataSource.mapper.mapToTrendMovieEntity
import com.sajjadio.trailers.data.dataSource.mapper.mapToUpcomingMovieEntity
import com.sajjadio.trailers.domain.mapper.mapToPopularMovieDomain
import com.sajjadio.trailers.domain.mapper.mapToGenresDomain
import com.sajjadio.trailers.data.dataSource.remote.MovieApiService
import com.sajjadio.trailers.data.paging.ComingPagingSource
import com.sajjadio.trailers.data.paging.MoviesOfGenresPagingSource
import com.sajjadio.trailers.data.paging.PopularPagingSource
import com.sajjadio.trailers.data.paging.RatedPagingSource
import com.sajjadio.trailers.data.paging.SimilarPagingData
import com.sajjadio.trailers.data.paging.SearchRemoteMediator
import com.sajjadio.trailers.domain.mapper.mapDtoToCommonResultMovieDomain
import com.sajjadio.trailers.domain.mapper.mapToImagesDomain
import com.sajjadio.trailers.domain.mapper.mapToMovieDetailsDomain
import com.sajjadio.trailers.domain.mapper.mapToPerson
import com.sajjadio.trailers.domain.mapper.mapToPersonOfMovie
import com.sajjadio.trailers.domain.mapper.mapToTopRatedMovieDomain
import com.sajjadio.trailers.domain.mapper.mapToTrendMovieDomain
import com.sajjadio.trailers.domain.mapper.mapToUpcomingMovieDomain
import com.sajjadio.trailers.domain.model.Cast
import com.sajjadio.trailers.domain.model.MovieDetails
import com.sajjadio.trailers.domain.model.CommonResult
import com.sajjadio.trailers.domain.model.GenresOfMovie
import com.sajjadio.trailers.domain.model.Person
import com.sajjadio.trailers.domain.model.Image
import com.sajjadio.trailers.domain.model.SearchMovieResult
import com.sajjadio.trailers.domain.model.TrendMovie
import com.sajjadio.trailers.domain.repository.MovieRepository
import com.sajjadio.trailers.utils.Constant
import com.sajjadio.trailers.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApiService,
    private val movieDetailsDao: MovieDetailsDao,
    private val movieDao: MovieDao,
    private val db: AppDatabase
) : MovieRepository {


    override suspend fun addMovie(movieDetails: MovieDetails) {
        movieDetailsDao.addMovie(mapDomainToMovieDetailsEntity(movieDetails))
    }

    override fun getAllSavedMovies(): Flow<List<MovieDetails>> {
        return mapLocalDataWithFlow(
            movieDetailsDao.getAllSavedMovies(),
            ::mapToMovieDetailsDomain
        )
    }

    override suspend fun deleteAllMovies() {
        return movieDetailsDao.deleteAllMovies()
    }

    override suspend fun checkIsMovieSaved(movieId: Int): Boolean {
        return movieDetailsDao.checkIsMovieSaved(movieId)
    }

    override suspend fun deleteMovie(movieDetails: MovieDetails) {
        return movieDetailsDao.deleteMovie(mapDomainToMovieDetailsEntity(movieDetails))
    }

    override suspend fun getTrendMovies(): Flow<List<TrendMovie>> {
        return mapLocalDataWithFlow(
            movieDao.getAllSavedTrendMovies(),
            ::mapToTrendMovieDomain
        )
    }

    override suspend fun getPopularMovies(): Flow<List<CommonResult>> {
        return mapLocalDataWithFlow(
            movieDao.getAllSavedPopularMovies(),
            ::mapToPopularMovieDomain
        )
    }

    override suspend fun getTopRatedMovies(): Flow<List<CommonResult>> {
        return mapLocalDataWithFlow(
            movieDao.getAllSavedTopRatedMovies(),
            ::mapToTopRatedMovieDomain
        )
    }

    override suspend fun getUpComingMovie(): Flow<List<CommonResult>> {
        return mapLocalDataWithFlow(
            movieDao.getAllSavedUpComingMovies(),
            ::mapToUpcomingMovieDomain
        )
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

    @OptIn(ExperimentalPagingApi::class)
    override fun getMovieSearch(query: String?): Flow<PagingData<SearchMovieResult>> {
        val pagingSourceFactory: () -> PagingSource<Int, SearchMovieResult> = {
           movieDao.getAllSavedSearchMovies(query)
        }

        return Pager(
            config = PagingConfig(
                pageSize = Constant.DEFAULT_PAGE_SIZE,
                prefetchDistance = Constant.PREFETCH_DISTANCE
            ),
            remoteMediator = SearchRemoteMediator(
                api = movieApi,
                db = db,
                query = query
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.flowOn(Dispatchers.IO)
    }


    override suspend fun getMovieDetails(id: Int): Resource<MovieDetails> {
        return mapWithResource({ movieApi.getMovieDetails(id) }) { movieDetailsDto ->
            mapToMovieDetailsDomain(movieDetailsDto)
        }
    }

    override suspend fun getImagesOfMovieById(movieId: Int?): Flow<Resource<List<Image>?>> {
        return mapRemoteWithFlow({ movieApi.getImagesOfMovieById(movieId) }) { imageDto ->
            mapToImagesDomain(imageDto.images)
        }
    }

    override suspend fun getImagesOfPersonById(personId: Int?): Flow<Resource<List<Image>?>> {
        return mapRemoteWithFlow({ movieApi.getImagesOfPersonById(personId) }) { imageDto ->
            mapToImagesDomain(imageDto.profileDto)
        }
    }

    override suspend fun getMoviesOfPersonById(personId: Int?): Flow<Resource<List<CommonResult>>> {
        return mapRemoteWithFlow({ movieApi.getMoviesOfPersonById(personId) }) { similar ->
            mapDtoToCommonResultMovieDomain(similar.cast)
        }
    }

    override fun getPersonOfMovieById(id: Int): Flow<Resource<List<Cast>?>> {
        return mapRemoteWithFlow({ movieApi.getPersonOfMovieById(id) }) { actors ->
            actors.cast?.let { castDto -> mapToPersonOfMovie(castDto) }
        }
    }

    override fun getPersonById(personId: Int?): Flow<Resource<Person>> {
        return mapRemoteWithFlow({ movieApi.getPersonById(personId) }) {
            mapToPerson(it)
        }
    }

    override suspend fun getSimilar(
        id: Int?,
        page: Int
    ): Flow<Resource<List<CommonResult>?>> {
        return mapRemoteWithFlow({ movieApi.getSimilar(id, page) }) { similar ->
            mapDtoToCommonResultMovieDomain(similar.results)
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

    override suspend fun getGenresMovie(): Flow<Resource<List<GenresOfMovie>>> {
        return mapRemoteWithFlow({
            movieApi.getGenresMovie()
        }) {
            mapToGenresDomain(it.genres)
        }
    }

    override suspend fun refreshHomeItems() {
        refreshWrapper(
            movieApi::getTrending,
            movieDao::addTrendMovies,
        ) {
            mapToTrendMovieEntity(it.results)
        }
        refreshWrapper(
            movieApi::getPopularMovie,
            movieDao::addPopularMovies,
        ) {
            mapToPopularMovieEntity(it.results)
        }
        refreshWrapper(
            movieApi::getTopRatedMovie,
            movieDao::addTopRatedMovies,
        ) {
            mapToTopRatedMovieEntity(it.results)
        }
        refreshWrapper(
            movieApi::getUpComingMovie,
            movieDao::addUpComingMovies,
        ) {
            mapToUpcomingMovieEntity(it.results)
        }
        refreshWrapper(
            movieApi::getUpComingMovie,
            movieDao::addUpComingMovies,
        ) {
            mapToUpcomingMovieEntity(it.results)
        }
    }

    private suspend fun <T, E> refreshWrapper(
        request: suspend () -> Response<T>,
        insertIntoDatabase: suspend (List<E>) -> Unit,
        mapper: (T) -> List<E>?,
    ) {
        try {
            request().also {
                if (it.isSuccessful) {
                    it.body()?.let { body ->
                        mapper(body)?.let { list ->
                            insertIntoDatabase(list)
                        }
                    }
                }
            }
        } catch (exception: Throwable) {
            Timber.d(exception.message)
        }
    }

    private fun <E, D> mapLocalDataWithFlow(
        data: Flow<List<E>>,
        mapper: (E) -> D
    ): Flow<List<D>> =
        data.map { list ->
            list.map { entity ->
                mapper(entity)
            }
        }

    private fun <I, O> mapRemoteWithFlow(
        function: suspend () -> Response<I>,
        mapper: (I) -> O
    ): Flow<Resource<O>> {
        return flow {
            try {
                val response = function()
                if (response.isSuccessful) {
                    emit(Resource.Success(response.body()?.let { mapper(it) }))
                } else {
                    emit(Resource.Error(response.message()))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    private suspend fun <I, O> mapWithResource(
        function: suspend () -> I,
        mapper: (I) -> O
    ): Resource<O> {
        return try {
            Resource.Success(mapper(function()))
        } catch (e: Throwable) {
            Resource.Error(e.message)
        }
    }

    private fun <T> wrapWithFlow(function: suspend () -> Response<T>): Flow<Resource<T>> {
        return flow {
            try {
                emit(checkIsSuccessfulResponse(function.invoke()))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    private fun <T> checkIsSuccessfulResponse(response: Response<T>): Resource<T> {
        return if (response.isSuccessful)
            Resource.Success(response.body())
        else
            Resource.Error(response.message())
    }
}