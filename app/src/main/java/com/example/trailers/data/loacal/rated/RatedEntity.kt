package com.example.trailers.data.loacal.rated

import com.google.gson.annotations.SerializedName

data class RatedEntity(
    @SerializedName("playNowResult")
    val ratedResult: List<RatedResultEntity>? = null,
)