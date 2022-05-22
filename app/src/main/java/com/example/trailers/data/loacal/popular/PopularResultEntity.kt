package com.example.trailers.data.loacal.popular

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.trailers.utils.ParentListAdapter
import com.google.gson.annotations.SerializedName


@Entity(
    tableName = "tb_popular",
    indices = [androidx.room.Index(value = ["id"], unique = true)]
)
data class PopularResultEntity(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("language")
    val language: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("poster")
    val poster: String? = null,
    @SerializedName("average")
    val average: Double? = null,
    @SerializedName("view")
    val view: Int? = null,
    @SerializedName("date")
    val date: String? = null,
) : ParentListAdapter
