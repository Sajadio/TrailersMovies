package com.example.trailers.data.network

import com.example.trailers.data.model.movie.actors.Actors
import com.example.trailers.data.model.movie.id.Genre
import com.example.trailers.data.model.movie.id.MovieID
import com.example.trailers.data.model.movie.playnow.MoviePlayNow
import com.example.trailers.data.model.movie.popular.PopularMovie
import com.example.trailers.data.model.movie.rate.TopRatedMovie
import com.example.trailers.data.model.movie.similar.Similar
import com.example.trailers.data.model.movie.upcoming.UPComingMovie
import com.example.trailers.data.model.search.Search
import com.example.trailers.utils.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing?")
    suspend fun getMoviePlayNow(
        @Query("api_key") key: String = Constant.API_KEY,
        @Query("page") page: Int = 1,
    ): MoviePlayNow

    @GET("movie/popular?")
    suspend fun getMoviePopular(
        @Query("api_key") key: String = Constant.API_KEY,
        @Query("page") page: Int = 1,
    ): PopularMovie

    @GET("movie/top_rated?")
    suspend fun getMovieTopRated(
        @Query("api_key") key: String = Constant.API_KEY,
        @Query("page") page: Int = 1,
    ): TopRatedMovie

    @GET("movie/upcoming?")
    suspend fun getUpComingMovie(
        @Query("api_key") key: String = Constant.API_KEY,
        @Query("page") page: Int = 1,
    ): UPComingMovie

    @GET("movie/{id}/videos?")
    suspend fun getMovieTrailer(
        @Path("id") id: Int,
        @Query("api_key") key: String,
    ): Response<Any>

    @GET("movie/{id}?")
    suspend fun getMoviesDetails(
        @Path("id") page: Int?,
        @Query("api_key") key: String = Constant.API_KEY
    ): MovieID

    @GET("movie/{id}/credits?")
    suspend fun getActors(
        @Path("id") page: Int?,
        @Query("api_key") key: String = Constant.API_KEY
    ): Actors

    @GET("movie/{id}/similar?")
    suspend fun getSimilar(
        @Path("id") page: Int?,
        @Query("api_key") key: String = Constant.API_KEY
    ): Similar


    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Query("api_key") key: String = Constant.API_KEY
    ): Genre

    @GET("search/multi?")
    suspend fun getMultiSearch(
        @Query("api_key") key: String = Constant.API_KEY,
        @Query("query") query: String,
        @Query("page") page: Int,
    ): Search
}