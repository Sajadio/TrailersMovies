package com.sajjadio.trailers.data.model.movie.actors

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.sajjadio.trailers.utils.ParentListAdapter

@SuppressLint("ParcelCreator")
data class Cast(
    val adult: Boolean?,
    val cast_id: Int?,
    val character: String?,
    val credit_id: String?,
    val gender: Int?,
    val id: Int,
    val known_for_department: String?,
    val name: String?,
    val order: Int?,
    val original_name: String?,
    val popularity: Double?,
    val profile_path: String?,
) : ParentListAdapter, Parcelable {
    override val item = id

    override fun describeContents(): Int = id

    override fun writeToParcel(p0: Parcel?, p1: Int) {
//        TODO("Not yet implemented")
    }
}
