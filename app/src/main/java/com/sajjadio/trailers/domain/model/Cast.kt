package com.sajjadio.trailers.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Cast(
    val id: Int,
    val original_name: String?,
    val profile_path: String?,
)