package com.sajjadio.trailers.data.model.movie.movie_details


import com.google.gson.annotations.SerializedName

data class ImageOfMovieDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("backdrops")
    val posters: List<PosterDto?>?
)