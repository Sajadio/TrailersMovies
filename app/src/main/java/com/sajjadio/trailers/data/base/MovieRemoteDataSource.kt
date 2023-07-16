package com.sajjadio.trailers.data.base

import com.sajjadio.trailers.data.dataSource.model.genre.Genre
import com.sajjadio.trailers.data.dataSource.model.movie.common.CommonDto
import com.sajjadio.trailers.data.dataSource.model.movie.movie_details.ImageOfMovieDto
import com.sajjadio.trailers.data.dataSource.model.movie.movie_details.MovieDetailsDto
import com.sajjadio.trailers.data.dataSource.model.movie.person.ImageOfPersonDto
import com.sajjadio.trailers.data.dataSource.model.movie.person.MoviesOfPersonDto
import com.sajjadio.trailers.data.dataSource.model.movie.person.PersonDto
import com.sajjadio.trailers.data.dataSource.model.movie.persons.PersonsDto
import com.sajjadio.trailers.data.dataSource.model.movie.search.SearchMovieDto
import com.sajjadio.trailers.data.dataSource.model.movie.trend.TrendMovieDto
import com.sajjadio.trailers.data.dataSource.model.movie.video.VideoMovieDto
import kotlinx.coroutines.flow.Flow


interface MovieRemoteDataSource {

    //----------    region home items  ----------
    suspend fun getTrending(lang:String): TrendMovieDto
    suspend fun getPopularMovie(page: Int,lang:String): CommonDto
    suspend fun getTopRatedMovie(page: Int,lang:String): CommonDto
    suspend fun getUpComingMovie(page: Int,lang:String): CommonDto
    //----------    endregion  ----------


    //----------    region movie details  ----------
    suspend fun getMovieById(movieId: Int?,lang:String): MovieDetailsDto
    suspend fun getImagesOfMovieById(movieId: Int?): ImageOfMovieDto
    suspend fun getPersonsOfMovieById(personId: Int?,lang:String): PersonsDto
    suspend fun getSimilarOfMovieById(id: Int?, page: Int?,lang:String): CommonDto
    //----------    endregion  ----------


    //----------    region person details  ----------
    suspend fun getPersonById(personId: Int?,lang:String): PersonDto
    suspend fun getImagesOfPersonById(personId: Int?): ImageOfPersonDto
    suspend fun getMoviesOfPersonById(personId: Int?,lang:String): MoviesOfPersonDto
    //----------    endregion  ----------


    //----------    region genres of movie  ----------
    suspend fun getGenresMovie(): Genre
    suspend fun getMoviesOfGenreById(genreId: Int, page: Int,lang:String): CommonDto
    //----------    endregion  ----------


    suspend fun getMovieTrailerById(movieId: Int): VideoMovieDto

    suspend fun getSearchMovieByQuery(query: String?, page: Int,lang:String): SearchMovieDto
}