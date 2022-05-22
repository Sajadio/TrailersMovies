package com.example.trailers.data.loacal.trend

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.trailers.utils.ParentListAdapter
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "tb_trend",
    indices = [androidx.room.Index(value = ["id"], unique = true)]
)
data class TrendResultEntity(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int?,
    @SerializedName("poster")
    val poster: String?,
):ParentListAdapter