package com.sajjadio.trailers.data.dataSource.model.movie.person


import com.google.gson.annotations.SerializedName
import com.sajjadio.trailers.data.dataSource.model.movie.common.ImageCommonDto

data class ImageOfPersonDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("profiles")
    val profileDto: List<com.sajjadio.trailers.data.dataSource.model.movie.common.ImageCommonDto?>?
)