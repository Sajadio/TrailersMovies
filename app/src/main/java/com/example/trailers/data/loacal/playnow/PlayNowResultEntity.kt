package com.example.trailers.data.loacal.playnow

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.trailers.utils.ParentListAdapter
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "tb_play_now",
    indices = [androidx.room.Index(value = ["id"], unique = true)]
)
data class PlayNowResultEntity(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("poster")
    val poster: String? = null,
) : ParentListAdapter
