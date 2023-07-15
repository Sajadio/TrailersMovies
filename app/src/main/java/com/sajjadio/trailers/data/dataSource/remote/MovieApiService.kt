package com.sajjadio.trailers.data.dataSource.remote

import com.sajjadio.trailers.data.base.MovieRemoteDataSource
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
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService : MovieRemoteDataSource {

    @GET("discover/movie")
    override suspend fun getMoviesOfGenreById(
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int
    ): CommonDto

    @GET("trending/movie/day?")
    override suspend fun getTrending(): TrendMovieDto

    @GET("movie/popular?")
    override suspend fun getPopularMovie(@Query("page") page: Int): CommonDto

    @GET("movie/top_rated?")
    override suspend fun getTopRatedMovie(@Query("page") page: Int): CommonDto

    @GET("movie/upcoming?")
    override suspend fun getUpComingMovie(@Query("page") page: Int): CommonDto

    @GET("movie/{id}/videos?")
    override suspend fun getMovieTrailer(@Path("id") id: Int?): VideoMovie

    @GET("movie/{id}?")
    override suspend fun getMovieDetails(@Path("id") movieId: Int?): MovieDetailsDto

    @GET("movie/{movie_id}/images?")
    override suspend fun getImagesOfMovieById(@Path("movie_id") movieId: Int?): ImageOfMovieDto

    @GET("person/{person_id}/images?")
    override suspend fun getImagesOfPersonById(@Path("person_id") personId: Int?): ImageOfPersonDto

    @GET("person/{person_id}/movie_credits?")
    override suspend fun getMoviesOfPersonById(@Path("person_id") personId: Int?): MoviesOfPersonDto

    @GET("movie/{id}/credits?")
    override suspend fun getPersonOfMovieById(@Path("id") personId: Int?): PersonsDto

    @GET("person/{id}")
    override suspend fun getPersonById(@Path("id") personId: Int?): PersonDto

    @GET("movie/{id}/similar?")
    override suspend fun getSimilar(@Path("id") id: Int?, @Query("page") page: Int?): CommonDto


    @GET("genre/movie/list")
    override suspend fun getGenresMovie(): Genre

    @GET("search/movie?")
    override suspend fun getSearchMovie(
        @Query("query") query: String?,
        @Query("page") page: Int
    ): SearchMovieDto


    @GET("person/{person_id}/movie_credits?")
    override suspend fun getMoviesOfActor(@Path("person_id") personId: Int?): ActorsMovie

}