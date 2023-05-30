package com.sajjadio.trailers.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sajjadio.trailers.data.model.movie.common.CommonResult
import com.sajjadio.trailers.data.model.movie.genremovie.MovieResult
import com.sajjadio.trailers.data.model.movie.search.SearchResult
import com.sajjadio.trailers.data.model.movie.similar.SimilarResult
import com.sajjadio.trailers.data.network.MovieApiService
import com.sajjadio.trailers.data.paging.ComingPagingSource
import com.sajjadio.trailers.data.paging.GenresPagingSource
import com.sajjadio.trailers.data.paging.PopularPagingSource
import com.sajjadio.trailers.data.paging.RatedPagingSource
import com.sajjadio.trailers.data.paging.MoviePagingData
import com.sajjadio.trailers.data.paging.SearchPagingSource
import com.sajjadio.trailers.utils.Constant
import com.sajjadio.trailers.utils.SafeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApiService,
) : MovieRepository, SafeApiCall {

    override suspend fun getTrendMovie() =
        safeApiCall { movieApi.getTrending() }.flowOn(Dispatchers.IO)

    override suspend fun getMoviePopular() =
        safeApiCall { movieApi.getPopularMovie() }.flowOn(Dispatchers.IO)

    override suspend fun getMovieTopRated() =
        safeApiCall { movieApi.getTopRatedMovie() }.flowOn(Dispatchers.IO)

    override suspend fun getUpComingMovie() =
        safeApiCall { movieApi.getUpComingMovie() }.flowOn(Dispatchers.IO)

    override fun listSimilarOfMovie(id: Int): Flow<PagingData<SimilarResult>> =
        Pager(config = PagingConfig(pageSize = Constant.DEFAULT_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = { MoviePagingData(api = movieApi, id = id) }
        ).flow.flowOn(Dispatchers.IO)

    override fun getMovieSearch(query: String?): Flow<PagingData<SearchResult>> =
        Pager(config = PagingConfig(pageSize = Constant.DEFAULT_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = { SearchPagingSource(api = movieApi, query = query) }
        ).flow

    override suspend fun getMoviesDetails(id: Int?) =
        safeApiCall { movieApi.getMoviesDetails(id) }.flowOn(Dispatchers.IO)

    override suspend fun getActors(id: Int?) =
        safeApiCall { movieApi.getActors(id) }.flowOn(Dispatchers.IO)

    override suspend fun getSimilar(id: Int?) =
        safeApiCall { movieApi.getSimilar(id, page = 1) }.flowOn(Dispatchers.IO)

    override suspend fun getMovieTrailer(id: Int?) =
        safeApiCall { movieApi.getMovieTrailer(id = id) }.flowOn(Dispatchers.IO)

    override suspend fun getMovieOfActor(person_id: Int?) =
        safeApiCall { movieApi.getMovieOfActor(person_id = person_id) }.flowOn(Dispatchers.IO)

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
        safeApiCall { movieApi.getGenresMovie() }.flowOn(Dispatchers.IO)
}