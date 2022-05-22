package com.example.trailers.data.loacal.playnow

import com.google.gson.annotations.SerializedName

data class PlayNowEntity(
    @SerializedName("playNowResult")
    val result: List<PlayNowResultEntity>? = null,
)