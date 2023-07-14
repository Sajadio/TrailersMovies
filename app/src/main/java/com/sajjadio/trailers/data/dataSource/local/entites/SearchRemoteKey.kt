package com.sajjadio.trailers.data.dataSource.local.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class SearchRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)