package com.sajjadio.trailers.data.model.movie.images


import com.google.gson.annotations.SerializedName

data class PosterDto(
    @SerializedName("aspect_ratio")
    val aspectRatio: Double?,
    @SerializedName("file_path")
    val filePath: String?,
    @SerializedName("height")
    val height: Int?,
    @SerializedName("iso_639_1")
    val iso6391: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?,
    @SerializedName("width")
    val width: Int?
)
