package com.sajjadio.trailers.data.model.movie.images


import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("backdrops")
    val posters: List<PosterDto?>?
)