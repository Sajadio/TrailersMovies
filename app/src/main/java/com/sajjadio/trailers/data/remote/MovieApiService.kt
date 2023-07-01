package com.sajjadio.trailers.data.remote

import com.sajjadio.trailers.data.model.movie.common.CommonDto
import com.sajjadio.trailers.data.model.movie.actors.Actors
import com.sajjadio.trailers.data.model.genre.Genre
import com.sajjadio.trailers.data.model.movie.actorsmovie.ActorsMovie
import com.sajjadio.trailers.data.model.movie.genremovie.Movie
import com.sajjadio.trailers.data.model.movie.movie_details.ImageOfMovieDto
import com.sajjadio.trailers.data.model.movie.movie_details.MovieDetailsDto
import com.sajjadio.trailers.data.model.movie.person.ImagesOfPersonDto
import com.sajjadio.trailers.data.model.movie.person.PersonDto
import com.sajjadio.trailers.data.model.movie.search.SearchMovie
import com.sajjadio.trailers.data.model.movie.trend.TrendMovieDto
import com.sajjadio.trailers.data.model.movie.video.VideoMovie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("discover/movie")
    suspend fun getGenreList(
        @Query("with_genres") genreId: String?,
        @Query("page") page: Int,
    ): Movie

    @GET("trending/movie/day?")
    suspend fun getTrending(
    ): Response<TrendMovieDto>

    @GET("movie/popular?")
    suspend fun getPopularMovie(
        @Query("page") page: Int = 1,
    ): Response<CommonDto>


    @GET("movie/top_rated?")
    suspend fun getTopRatedMovie(
        @Query("page") page: Int = 1,
    ): Response<CommonDto>

    @GET("movie/upcoming?")
    suspend fun getUpComingMovie(
        @Query("page") page: Int = 1,
    ): Response<CommonDto>

    @GET("movie/{id}/videos?")
    suspend fun getMovieTrailer(
        @Path("id") id: Int?,
    ): Response<VideoMovie>

    @GET("movie/{id}?")
    suspend fun getMovieDetails(
        @Path("id") movieId: Int?,
    ): Response<MovieDetailsDto>

    @GET("movie/{movie_id}/images?")
    suspend fun getImagesOfMovieById(
        @Path("movie_id") movieId: Int?
    ): Response<ImageOfMovieDto>

    @GET("person/{person_id}/images?")
    suspend fun getImagesOfPersonById(
        @Path("person_id") personId: Int?
    ): Response<ImagesOfPersonDto>

    @GET("movie/{id}/credits?")
    suspend fun getPersonOfMovieById(
        @Path("id") id: Int?,
    ): Response<Actors>

    @GET("person/{id}")
    suspend fun getPersonById(
        @Path("id") personId: Int?,
    ): Response<PersonDto>

    @GET("movie/{id}/similar?")
    suspend fun getSimilar(
        @Path("id") id: Int?,
        @Query("page") page: Int?,
    ): Response<CommonDto>


    @GET("genre/movie/list")
    suspend fun getGenresMovie(
    ): Response<Genre>

    @GET("search/movie?")
    suspend fun getSearchMovie(
        @Query("query") query: String?,
        @Query("page") page: Int,
    ): Response<SearchMovie>


    @GET("person/{person_id}/movie_credits?")
    suspend fun getMoviesOfActor(
        @Path("person_id") personId: Int?,
    ): Response<ActorsMovie>

}