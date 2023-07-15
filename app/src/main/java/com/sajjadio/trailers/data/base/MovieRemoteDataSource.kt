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
import com.sajjadio.trailers.data.dataSource.model.movie.video.VideoMovie


interface MovieRemoteDataSource {

    //----------    region home items  ----------
    suspend fun getTrending(): TrendMovieDto
    suspend fun getPopularMovie(page: Int): CommonDto
    suspend fun getTopRatedMovie(page: Int): CommonDto
    suspend fun getUpComingMovie(page: Int): CommonDto
    //----------    endregion  ----------


    //----------    region movie details  ----------
    suspend fun getMovieById(movieId: Int?): MovieDetailsDto
    suspend fun getImagesOfMovieById(movieId: Int?): ImageOfMovieDto
    suspend fun getPersonsOfMovieById(personId: Int?): PersonsDto
    suspend fun getSimilarOfMovieById(id: Int?, page: Int?): CommonDto
    //----------    endregion  ----------


    //----------    region person details  ----------
    suspend fun getPersonById(personId: Int?): PersonDto
    suspend fun getImagesOfPersonById(personId: Int?): ImageOfPersonDto
    suspend fun getMoviesOfPersonById(personId: Int?): MoviesOfPersonDto
    //----------    endregion  ----------


    //----------    region genres of movie  ----------
    suspend fun getGenresMovie(): Genre
    suspend fun getMoviesOfGenreById(genreId: Int, page: Int): CommonDto
    //----------    endregion  ----------


    suspend fun getMovieTrailerById(id: Int?): VideoMovie
    suspend fun getSearchMovieByQuery(query: String?, page: Int): SearchMovieDto
}