package com.sajjadio.trailers.data.network

import com.sajjadio.trailers.data.model.movie.common.Common
import com.sajjadio.trailers.data.model.movie.actors.Actors
import com.sajjadio.trailers.data.model.genre.Genre
import com.sajjadio.trailers.data.model.movie.actorsmovie.ActorsMovie
import com.sajjadio.trailers.data.model.movie.genremovie.Movie
import com.sajjadio.trailers.data.model.movie.id.IDMovie
import com.sajjadio.trailers.data.model.movie.search.SearchMovie
import com.sajjadio.trailers.data.model.movie.similar.Similar
import com.sajjadio.trailers.data.model.movie.trend.TrendMovie
import com.sajjadio.trailers.data.model.movie.video.VideoMovie
import com.sajjadio.trailers.utils.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    suspend fun getGenreList(
        @Query("api_key") apiKey: String = Constant.API_KEY,
        @Query("with_genres") genreId: String?,
        @Query("page") page: Int,
    ): Movie

    @GET("trending/all/day?")
    suspend fun getTrending(
        @Query("api_key") key: String = Constant.API_KEY,
    ): Response<TrendMovie>

    @GET("movie/popular?")
    suspend fun getPopularMovie(
        @Query("api_key") key: String = Constant.API_KEY,
        @Query("page") page: Int = 1,
    ): Response<Common>


    @GET("movie/top_rated?")
    suspend fun getTopRatedMovie(
        @Query("api_key") key: String = Constant.API_KEY,
        @Query("page") page: Int = 1,
    ): Response<Common>

    @GET("movie/upcoming?")
    suspend fun getUpComingMovie(
        @Query("api_key") key: String = Constant.API_KEY,
        @Query("page") page: Int = 1,
    ): Response<Common>

    @GET("movie/{id}/videos?")
    suspend fun getMovieTrailer(
        @Path("id") id: Int?,
        @Query("api_key") key: String = Constant.API_KEY,
    ): Response<VideoMovie>

    @GET("movie/{id}?")
    suspend fun getMoviesDetails(
        @Path("id") page: Int?,
        @Query("api_key") key: String = Constant.API_KEY,
    ): Response<IDMovie>

    @GET("movie/{id}/credits?")
    suspend fun getActors(
        @Path("id") page: Int?,
        @Query("api_key") key: String = Constant.API_KEY,
    ): Response<Actors>

    @GET("movie/{id}/similar?")
    suspend fun getSimilar(
        @Path("id") id: Int?,
        @Query("api_key") key: String = Constant.API_KEY,
        @Query("page") page: Int?,
    ): Response<Similar>


    @GET("genre/movie/list")
    suspend fun getGenresMovie(
        @Query("api_key") key: String = Constant.API_KEY,
    ): Response<Genre>

    @GET("search/movie?")
    suspend fun getSearchMovie(
        @Query("api_key") key: String = Constant.API_KEY,
        @Query("query") query: String?,
        @Query("page") page: Int,
    ): SearchMovie


    @GET("person/{person_id}/movie_credits?")
    suspend fun getMovieOfActor(
        @Path("person_id") person_id: Int?,
        @Query("api_key") key: String = Constant.API_KEY,
    ): Response<ActorsMovie>

}