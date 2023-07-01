package com.sajjadio.trailers.data.model.movie.person


import com.google.gson.annotations.SerializedName
import com.sajjadio.trailers.data.model.movie.common.CommonResultDto

data class MoviesOfPersonDto(
    @SerializedName("cast")
    val cast: List<CommonResultDto>,
    @SerializedName("crew")
    val crew: List<Any?>?,
    @SerializedName("id")
    val id: Int?
)