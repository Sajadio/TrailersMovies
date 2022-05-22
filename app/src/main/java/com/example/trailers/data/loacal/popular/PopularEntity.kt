package com.example.trailers.data.loacal.popular

import com.google.gson.annotations.SerializedName

data class PopularEntity(
    @SerializedName("ratedResult")
    val popularResult: List<PopularResultEntity>? = null,
)
