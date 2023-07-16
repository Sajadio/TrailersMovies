package com.sajjadio.trailers.data.dataSource.model.movie.video


import com.google.gson.annotations.SerializedName

data class VideoMovieDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("results")
    val results: List<VideoResultDto?>?
)