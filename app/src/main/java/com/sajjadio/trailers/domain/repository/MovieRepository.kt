package com.sajjadio.trailers.domain.repository

import androidx.paging.PagingData
import com.sajjadio.trailers.domain.model.Cast
import com.sajjadio.trailers.domain.model.MovieDetails
import com.sajjadio.trailers.domain.model.CommonResult
import com.sajjadio.trailers.domain.model.FavoriteMovie
import com.sajjadio.trailers.domain.model.GenresOfMovie
import com.sajjadio.trailers.domain.model.Person
import com.sajjadio.trailers.domain.model.Image
import com.sajjadio.trailers.domain.model.SearchMovieResult
import com.sajjadio.trailers.domain.model.TrendMovie
import com.sajjadio.trailers.domain.model.Video
import com.sajjadio.trailers.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    //----------    region home items  ----------
    fun getTrendMovies(lang: String): Flow<List<TrendMovie>>
    fun getPopularMovies(lang: String): Flow<List<CommonResult>>
    fun getTopRatedMovies(lang: String): Flow<List<CommonResult>>
    fun getUpComingMovie(lang: String): Flow<List<CommonResult>>
    //----------    endregion  ----------


    //----------    region movie details  ----------
    suspend fun getMovieById(id: Int, lang: String, isSavedItem: Boolean?): Resource<MovieDetails>
    suspend fun getImagesOfMovieById(movieId: Int?): Flow<Resource<List<Image>?>>
    fun getPersonsOfMovieById(id: Int, lang: String): Flow<Resource<List<Cast>?>>
    suspend fun getSimilarOfMovieById(
        id: Int?,
        page: Int,
        lang: String
    ): Flow<Resource<List<CommonResult>?>>
    //----------    endregion  ----------


    //----------    region person details  ----------
    fun getPersonById(personId: Int?, lang: String): Flow<Resource<Person>>
    suspend fun getImagesOfPersonById(personId: Int?): Flow<Resource<List<Image>?>>
    suspend fun getMoviesOfPersonById(
        personId: Int?,
        lang: String
    ): Flow<Resource<List<CommonResult>>>
    //----------    endregion  ----------


    //----------    region common data  ----------
    fun getSimilarOfMovie(id: Int, lang: String): Flow<PagingData<CommonResult>>
    fun getPopularMoviePaging(lang: String): Flow<PagingData<CommonResult>>
    fun getTopRatedMoviePaging(lang: String): Flow<PagingData<CommonResult>>
    fun getUpComingMoviePaging(lang: String): Flow<PagingData<CommonResult>>
    //----------    endregion  ----------


    //----------    region genres of movie  ----------
    suspend fun getGenresMovie(lang: String): Flow<Resource<List<GenresOfMovie>>>
    fun getMoviesOfGenreById(genreId: Int, lang: String): Flow<PagingData<CommonResult>>
    //----------    endregion  ----------


    suspend fun getMovieTrailer(id: Int): Flow<Resource<List<Video>?>>
    fun getMovieSearchByQuery(query: String?, lang: String): Flow<PagingData<SearchMovieResult>>


    //----------    region local db of movies  ----------
    suspend fun addMovie(movieDetails: MovieDetails)
    fun getAllSavedMovies(): Flow<List<FavoriteMovie>>
    suspend fun deleteAllMovies()
    suspend fun checkIsMovieSaved(movieId: Int): Flow<Boolean>
    suspend fun deleteMovie(movieId:Int)
    suspend fun refreshHomeItems(page: Int, lang: String)
    //----------    endregion  ----------

}
