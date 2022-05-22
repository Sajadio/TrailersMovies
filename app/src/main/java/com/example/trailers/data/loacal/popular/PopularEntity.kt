package com.example.trailers.data.loacal.popular

import com.google.gson.annotations.SerializedName

data class PopularEntity(
    @SerializedName("playNowResult")
    val popularResult: List<PopularResultEntity>? = null,
)
