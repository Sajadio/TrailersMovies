package com.example.movie.domain.network

import com.example.movie.data.m.Trend
import com.example.movie.domain.model.movie.search.Search
import com.example.movie.domain.model.movie.trend.Trending
import com.example.movie.utils.Constant
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("trending/movie/day?")
    fun getMovieTrending(
        @Query("api_key") key: String = Constant.API_KEY,
        @Query("page") page: Int,
    ): Flow<Trending>

    @GET("movie/popular?")
    suspend fun getMoviePopular(
        @Query("api_key") key: String,
        @Query("page") page: Int,
    ): Response<Any>

    @GET("movie/{id}/videos?")
    suspend fun getMovieTrailer(
        @Path("id") id: Int,
        @Query("api_key") key: String,
    ): Response<Any>

    @GET("movie/{id}?")
    suspend fun getMoviesDetails(
        @Path("id") page: Int,
        @Query("api_key") key: String,
    ): Response<Any>

    @GET("search/movie?")
    suspend fun getMoviesSearch(
        @Query("api_key") key: String = Constant.API_KEY,
        @Query("query") query: String,
    ): Response<Search>

    @GET("/genre/movie/list")
    suspend fun getMovieGenres(
        @Query("api_key") key: String,
    )

    /*----------------------------------TV-----------------------------------------*/

    @GET("trending/tv/day?")
    suspend fun getTVTrending(
        @Query("api_key") key: String,
        @Query("page") page: Int,
    ): Response<Any>

    @GET("tv/popular?")
    suspend fun getTVPopular(
        @Query("api_key") key: String,
        @Query("page") page: Int,
    ): Response<Any>

    @GET("tv/{id}/videos?")
    suspend fun getTVTrailer(
        @Path("id") id: Int,
        @Query("api_key") key: String,
    ): Response<Any>

    @GET("tv/{id}?")
    suspend fun getTvDetails(
        @Path("id") page: Int,
        @Query("api_key") key: String,
    ): Response<Any>

    @GET("search/tv?")
    suspend fun getTvSearch(
        @Query("api_key") key: String,
        @Query("query") query: String,
    ): Response<Any>

    @GET("/tv/{tv_id}/season/{season_number}")
    suspend fun getSeasons(
        @Path("tv_id") tv_id: Int,
        @Path("season_number") season_number: Int,
        @Query("api_key") key: String,
    )

    @GET("/tv/{tv_id}/season/{season_number}/episode/{episode_number}/rating")
    suspend fun getEpisode(
        @Path("id") id: Int,
        @Path("season_number") season_number: Int,
        @Path("episode_number") episode_number: Int,
        @Query("api_key") key: String,
    )

    @GET("/genre/movie/list")
    suspend fun getTVGenres(
        @Query("api_key") key: String,
    )


}