package com.sajjadio.trailers.domain.repository

import androidx.paging.PagingData
import com.sajjadio.trailers.data.dataSource.model.movie.actorsmovie.ActorsMovie
import com.sajjadio.trailers.data.dataSource.model.movie.video.VideoMovie
import com.sajjadio.trailers.domain.model.Cast
import com.sajjadio.trailers.domain.model.MovieDetails
import com.sajjadio.trailers.domain.model.CommonResult
import com.sajjadio.trailers.domain.model.GenresOfMovie
import com.sajjadio.trailers.domain.model.Person
import com.sajjadio.trailers.domain.model.Image
import com.sajjadio.trailers.domain.model.SearchMovieResult
import com.sajjadio.trailers.domain.model.TrendMovie
import com.sajjadio.trailers.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun addMovie(movieDetails: MovieDetails)
    fun getAllSavedMovies(): Flow<List<MovieDetails>>
    suspend fun deleteAllMovies()
    suspend fun checkIsMovieSaved(movieId: Int): Boolean
    suspend fun deleteMovie(movieDetails: MovieDetails)
    suspend fun getTrendMovies(): Flow<List<TrendMovie>>
    suspend fun getPopularMovies(): Flow<List<CommonResult>>
    suspend fun getTopRatedMovies(): Flow<List<CommonResult>>
    suspend fun getUpComingMovie(): Flow<List<CommonResult>>
    suspend fun getMovieDetails(id: Int): Resource<MovieDetails>
    suspend fun getImagesOfMovieById(movieId: Int?): Flow<Resource<List<Image>?>>
    suspend fun getImagesOfPersonById(personId: Int?): Flow<Resource<List<Image>?>>
    suspend fun getMoviesOfPersonById(personId: Int?): Flow<Resource<List<CommonResult>>>
    fun getPersonOfMovieById(id: Int): Flow<Resource<List<Cast>?>>
    fun getPersonById(personId: Int?): Flow<Resource<Person>>
    suspend fun getSimilar(id: Int?, page: Int): Flow<Resource<List<CommonResult>?>>
    suspend fun getMovieTrailer(id: Int?): Flow<Resource<VideoMovie?>>
    suspend fun getMovieOfActor(person_id: Int?): Flow<Resource<ActorsMovie?>>
    fun getMovieSearch(query: String?): Flow<PagingData<SearchMovieResult>>
    fun getMoviesOfGenreById(genreId: Int): Flow<PagingData<CommonResult>>
    suspend fun getGenresMovie(): Flow<Resource<List<GenresOfMovie>>>
    fun getSimilarOfMovie(id: Int): Flow<PagingData<CommonResult>>
    fun getPopularMoviePaging(): Flow<PagingData<CommonResult>>
    fun getTopRatedMoviePaging(): Flow<PagingData<CommonResult>>
    fun getUpComingMoviePaging(): Flow<PagingData<CommonResult>>
    suspend fun refreshHomeItems(page: Int)
}