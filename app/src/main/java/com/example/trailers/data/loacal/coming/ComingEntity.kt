package com.example.trailers.data.loacal.coming

import com.google.gson.annotations.SerializedName

data class ComingEntity(
    @SerializedName("ratedResult")
    val comingResult: List<ComingResultEntity>? = null,
)