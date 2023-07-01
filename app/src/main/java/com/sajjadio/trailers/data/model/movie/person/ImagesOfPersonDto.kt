package com.sajjadio.trailers.data.model.movie.person


import com.google.gson.annotations.SerializedName

data class ImagesOfPersonDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("profiles")
    val profileDto: List<ProfileDto?>?
)