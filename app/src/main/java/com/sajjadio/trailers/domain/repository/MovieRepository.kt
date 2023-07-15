package com.sajjadio.trailers.domain.repository

import androidx.paging.PagingData
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

    //----------    region home items  ----------
    fun getTrendMovies(): Flow<List<TrendMovie>>
    fun getPopularMovies(): Flow<List<CommonResult>>
    fun getTopRatedMovies(): Flow<List<CommonResult>>
    fun getUpComingMovie(): Flow<List<CommonResult>>
    //----------    endregion  ----------


    //----------    region movie details  ----------
    suspend fun getMovieById(id: Int): Resource<MovieDetails>
    suspend fun getImagesOfMovieById(movieId: Int?): Flow<Resource<List<Image>?>>
    fun getPersonsOfMovieById(id: Int): Flow<Resource<List<Cast>?>>
    suspend fun getSimilarOfMovieById(id: Int?, page: Int): Flow<Resource<List<CommonResult>?>>
    //----------    endregion  ----------


    //----------    region person details  ----------
    fun getPersonById(personId: Int?): Flow<Resource<Person>>
    suspend fun getImagesOfPersonById(personId: Int?): Flow<Resource<List<Image>?>>
    suspend fun getMoviesOfPersonById(personId: Int?): Flow<Resource<List<CommonResult>>>
    //----------    endregion  ----------


    //----------    region common data  ----------
    fun getSimilarOfMovie(id: Int): Flow<PagingData<CommonResult>>
    fun getPopularMoviePaging(): Flow<PagingData<CommonResult>>
    fun getTopRatedMoviePaging(): Flow<PagingData<CommonResult>>
    fun getUpComingMoviePaging(): Flow<PagingData<CommonResult>>
    //----------    endregion  ----------


    //----------    region genres of movie  ----------
    suspend fun getGenresMovie(): Flow<Resource<List<GenresOfMovie>>>
    fun getMoviesOfGenreById(genreId: Int): Flow<PagingData<CommonResult>>
    //----------    endregion  ----------


    suspend fun getMovieTrailer(id: Int?): Flow<Resource<VideoMovie?>>
    fun getMovieSearch(query: String?): Flow<PagingData<SearchMovieResult>>


    //----------    region local db of movies  ----------
    suspend fun addMovie(movieDetails: MovieDetails)
    fun getAllSavedMovies(): Flow<List<MovieDetails>>
    suspend fun deleteAllMovies()
    suspend fun checkIsMovieSaved(movieId: Int): Boolean
    suspend fun deleteMovie(movieDetails: MovieDetails)
    suspend fun refreshHomeItems(page: Int)
    //----------    endregion  ----------

}
