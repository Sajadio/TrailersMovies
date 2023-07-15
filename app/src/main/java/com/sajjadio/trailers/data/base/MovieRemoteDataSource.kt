package com.sajjadio.trailers.data.base

import com.sajjadio.trailers.data.dataSource.model.genre.Genre
import com.sajjadio.trailers.data.dataSource.model.movie.actorsmovie.ActorsMovie
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
    suspend fun getMoviesOfGenreById(genreId: Int, page: Int): CommonDto
    suspend fun getTrending(): TrendMovieDto
    suspend fun getPopularMovie(page: Int): CommonDto
    suspend fun getTopRatedMovie(page: Int): CommonDto
    suspend fun getUpComingMovie(page: Int): CommonDto
    suspend fun getMovieTrailer(id: Int?): VideoMovie
    suspend fun getMovieDetails(movieId: Int?): MovieDetailsDto
    suspend fun getImagesOfMovieById(movieId: Int?): ImageOfMovieDto
    suspend fun getImagesOfPersonById(personId: Int?): ImageOfPersonDto
    suspend fun getMoviesOfPersonById(personId: Int?): MoviesOfPersonDto
    suspend fun getPersonOfMovieById(personId: Int?): PersonsDto
    suspend fun getPersonById(personId: Int?): PersonDto
    suspend fun getSimilar(id: Int?, page: Int?): CommonDto
    suspend fun getGenresMovie(): Genre
    suspend fun getSearchMovie(query: String?, page: Int): SearchMovieDto
    suspend fun getMoviesOfActor(personId: Int?): ActorsMovie
}