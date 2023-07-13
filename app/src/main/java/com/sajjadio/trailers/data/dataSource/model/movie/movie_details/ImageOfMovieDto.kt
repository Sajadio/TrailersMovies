package com.sajjadio.trailers.data.dataSource.model.movie.movie_details


import com.google.gson.annotations.SerializedName
import com.sajjadio.trailers.data.dataSource.model.movie.common.ImageCommonDto

data class ImageOfMovieDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("backdrops")
    val images: List<com.sajjadio.trailers.data.dataSource.model.movie.common.ImageCommonDto?>?
)