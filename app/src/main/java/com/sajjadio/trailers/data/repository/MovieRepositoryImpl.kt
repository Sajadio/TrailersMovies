package com.sajjadio.trailers.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.sajjadio.trailers.data.base.FavoriteMovieLocalDataSource
import com.sajjadio.trailers.data.base.MovieLocalDataSource
import com.sajjadio.trailers.data.base.MovieRemoteDataSource
import com.sajjadio.trailers.data.base.SearchMovieLocalDataSource
import com.sajjadio.trailers.data.dataSource.local.AppDatabase
import com.sajjadio.trailers.data.dataSource.mapper.mapDomainToMovieDetailsEntity
import com.sajjadio.trailers.data.dataSource.mapper.mapToPopularMovieEntity
import com.sajjadio.trailers.data.dataSource.mapper.mapToTopRatedMovieEntity
import com.sajjadio.trailers.data.dataSource.mapper.mapToTrendMovieEntity
import com.sajjadio.trailers.data.dataSource.mapper.mapToUpcomingMovieEntity
import com.sajjadio.trailers.domain.mapper.mapToPopularMovieDomain
import com.sajjadio.trailers.domain.mapper.mapToGenresDomain
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
import timber.log.Timber
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val favoriteMovieLocalDataSource: FavoriteMovieLocalDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val searchMovieLocalDataSource: SearchMovieLocalDataSource,
    private val db: AppDatabase
) : MovieRepository {

    //----------    region home items  ----------
    override fun getTrendMovies(): Flow<List<TrendMovie>> {
        return mapLocalDataToFlowList(
            movieLocalDataSource.getAllSavedTrendMovies(),
            ::mapToTrendMovieDomain
        )
    }

    override fun getPopularMovies(): Flow<List<CommonResult>> {
        return mapLocalDataToFlowList(
            movieLocalDataSource.getAllSavedPopularMovies(),
            ::mapToPopularMovieDomain
        )
    }

    override fun getTopRatedMovies(): Flow<List<CommonResult>> {
        return mapLocalDataToFlowList(
            movieLocalDataSource.getAllSavedTopRatedMovies(),
            ::mapToTopRatedMovieDomain
        )
    }

    override fun getUpComingMovie(): Flow<List<CommonResult>> {
        return mapLocalDataToFlowList(
            movieLocalDataSource.getAllSavedUpComingMovies(),
            ::mapToUpcomingMovieDomain
        )
    }
    //----------    endregion  ----------


    //----------    region movie details  ----------
    override suspend fun getMovieById(id: Int): Resource<MovieDetails> {
        return mapRemoteDataToResource({ movieRemoteDataSource.getMovieById(id) }) { movieDetailsDto ->
            mapToMovieDetailsDomain(movieDetailsDto)
        }
    }

    override suspend fun getImagesOfMovieById(movieId: Int?): Flow<Resource<List<Image>?>> {
        return mapRemoteDataToFlowResource({ movieRemoteDataSource.getImagesOfMovieById(movieId) }) { imageDto ->
            mapToImagesDomain(imageDto.images)
        }
    }

    override fun getPersonsOfMovieById(id: Int): Flow<Resource<List<Cast>?>> {
        return mapRemoteDataToFlowResource({ movieRemoteDataSource.getPersonsOfMovieById(id) }) { actors ->
            actors.cast?.let { castDto -> mapToPersonOfMovie(castDto) }
        }
    }

    override suspend fun getSimilarOfMovieById(
        id: Int?,
        page: Int
    ): Flow<Resource<List<CommonResult>?>> {
        return mapRemoteDataToFlowResource({
            movieRemoteDataSource.getSimilarOfMovieById(id, page)
        }) { similar ->
            mapDtoToCommonResultMovieDomain(similar.results)
        }
    }
    //----------    endregion  ----------


    //----------    region person details  ----------
    override fun getPersonById(personId: Int?): Flow<Resource<Person>> {
        return mapRemoteDataToFlowResource({ movieRemoteDataSource.getPersonById(personId) }) {
            mapToPerson(it)
        }
    }

    override suspend fun getImagesOfPersonById(personId: Int?): Flow<Resource<List<Image>?>> {
        return mapRemoteDataToFlowResource({ movieRemoteDataSource.getImagesOfPersonById(personId) }) { imageDto ->
            mapToImagesDomain(imageDto.profileDto)
        }
    }

    override suspend fun getMoviesOfPersonById(personId: Int?): Flow<Resource<List<CommonResult>>> {
        return mapRemoteDataToFlowResource({ movieRemoteDataSource.getMoviesOfPersonById(personId) }) { similar ->
            mapDtoToCommonResultMovieDomain(similar.cast)
        }
    }
    //----------    endregion  ----------


    //----------    region common data  ----------
    override fun getSimilarOfMovie(id: Int): Flow<PagingData<CommonResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constant.DEFAULT_PAGE_SIZE,
                prefetchDistance = Constant.PREFETCH_DISTANCE
            ), pagingSourceFactory = {
                SimilarPagingData(
                    movieRemoteDataSource = movieRemoteDataSource,
                    id = id,
                )
            }
        ).flow.flowOn(Dispatchers.IO)
    }

    override fun getPopularMoviePaging(): Flow<PagingData<CommonResult>> =
        Pager(config = PagingConfig(pageSize = Constant.DEFAULT_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = {
                PopularPagingSource(movieRemoteDataSource = movieRemoteDataSource)
            }
        ).flow.flowOn(Dispatchers.IO)

    override fun getTopRatedMoviePaging(): Flow<PagingData<CommonResult>> =
        Pager(config = PagingConfig(pageSize = Constant.DEFAULT_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = {
                RatedPagingSource(movieRemoteDataSource = movieRemoteDataSource)
            }
        ).flow.flowOn(Dispatchers.IO)

    override fun getUpComingMoviePaging(): Flow<PagingData<CommonResult>> =
        Pager(config = PagingConfig(pageSize = Constant.DEFAULT_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = {
                ComingPagingSource(movieRemoteDataSource = movieRemoteDataSource)
            }
        ).flow.flowOn(Dispatchers.IO)
    //----------    endregion  ----------


    //----------    region genres of movie  ----------
    override suspend fun getGenresMovie(): Flow<Resource<List<GenresOfMovie>>> {
        return mapRemoteDataToFlowResource({
            movieRemoteDataSource.getGenresMovie()
        }) {
            mapToGenresDomain(it.genres)
        }
    }

    override fun getMoviesOfGenreById(genreId: Int): Flow<PagingData<CommonResult>> =
        Pager(config = PagingConfig(pageSize = Constant.DEFAULT_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = {
                MoviesOfGenresPagingSource(
                    genreId = genreId,
                    movieRemoteDataSource = movieRemoteDataSource
                )
            }
        ).flow.flowOn(Dispatchers.IO)
    //----------    endregion  ----------


    override suspend fun getMovieTrailer(id: Int?) =
        wrapWithFlow { movieRemoteDataSource.getMovieTrailerById(id = id) }.flowOn(Dispatchers.IO)

    @OptIn(ExperimentalPagingApi::class)
    override fun getMovieSearch(query: String?): Flow<PagingData<SearchMovieResult>> {
        val pagingSourceFactory: () -> PagingSource<Int, SearchMovieResult> = {
            movieLocalDataSource.getAllSavedSearchMovies(query)
        }

        return Pager(
            config = PagingConfig(
                pageSize = Constant.DEFAULT_PAGE_SIZE,
                prefetchDistance = Constant.PREFETCH_DISTANCE
            ),
            remoteMediator = SearchRemoteMediator(
                movieRemoteDataSource = movieRemoteDataSource,
                db = db,
                query = query,
                searchMovieLocalDataSource = searchMovieLocalDataSource,
                movieLocalDataSource = movieLocalDataSource
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.flowOn(Dispatchers.IO)
    }


    //----------    region local db of movies  ----------
    override suspend fun addMovie(movieDetails: MovieDetails) {
        favoriteMovieLocalDataSource.addMovie(mapDomainToMovieDetailsEntity(movieDetails))
    }

    override fun getAllSavedMovies(): Flow<List<MovieDetails>> {
        return mapLocalDataToFlowList(
            favoriteMovieLocalDataSource.getAllSavedMovies(),
            ::mapToMovieDetailsDomain
        )
    }

    override suspend fun deleteAllMovies() {
        return favoriteMovieLocalDataSource.deleteAllMovies()
    }

    override suspend fun checkIsMovieSaved(movieId: Int): Boolean {
        return favoriteMovieLocalDataSource.checkIsMovieSaved(movieId)
    }

    override suspend fun deleteMovie(movieDetails: MovieDetails) {
        return favoriteMovieLocalDataSource.deleteMovie(mapDomainToMovieDetailsEntity(movieDetails))
    }

    override suspend fun refreshHomeItems(page: Int) {
        refreshDataInLocalStorage(
            movieRemoteDataSource::getTrending,
            movieLocalDataSource::addTrendMovies,
        ) {
            mapToTrendMovieEntity(it.results)
        }
        refreshDataInLocalStorage(
            { movieRemoteDataSource.getPopularMovie(page) },
            movieLocalDataSource::addPopularMovies,
        ) {
            mapToPopularMovieEntity(it.results)
        }
        refreshDataInLocalStorage(
            { movieRemoteDataSource.getTopRatedMovie(page) },
            movieLocalDataSource::addTopRatedMovies,
        ) {
            mapToTopRatedMovieEntity(it.results)
        }
        refreshDataInLocalStorage(
            { movieRemoteDataSource.getUpComingMovie(page) },
            movieLocalDataSource::addUpComingMovies,
        ) {
            mapToUpcomingMovieEntity(it.results)
        }
        refreshDataInLocalStorage(
            { movieRemoteDataSource.getUpComingMovie(page) },
            movieLocalDataSource::addUpComingMovies,
        ) {
            mapToUpcomingMovieEntity(it.results)
        }
    }
    //----------    endregion  ----------


    //----------    region Base functions  ----------
    private suspend fun <T, E> refreshDataInLocalStorage(
        request: suspend () -> T,
        refreshDataInLocalStorage: suspend (List<E>) -> Unit,
        mapResponse: (T) -> List<E>?,
    ) {
        try {
            request().also {
                it?.let { body ->
                    mapResponse(body)?.let { list ->
                        refreshDataInLocalStorage(list)
                    }
                }
            }
        } catch (exception: Throwable) {
            Timber.d(exception.message)
        }
    }

    private fun <L, D> mapLocalDataToFlowList(
        data: Flow<List<L>>,
        mapResponse: (L) -> D
    ): Flow<List<D>> =
        data.map { list ->
            list.map { entity ->
                mapResponse(entity)
            }
        }

    private fun <I, O> mapRemoteDataToFlowResource(
        function: suspend () -> I,
        mapResponse: (I) -> O
    ): Flow<Resource<O>> {
        return flow {
            try {
                val response = function()
                emit(Resource.Success(response?.let { mapResponse(it) }))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    private suspend fun <I, O> mapRemoteDataToResource(
        function: suspend () -> I,
        mapResponse: (I) -> O
    ): Resource<O> {
        return try {
            Resource.Success(mapResponse(function()))
        } catch (e: Throwable) {
            Resource.Error(e.message)
        }
    }

    private fun <T> wrapWithFlow(function: suspend () -> T): Flow<Resource<T>> {
        return flow {
            try {
                emit(Resource.Success(function.invoke()))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }
    //----------    endregion  ----------

}