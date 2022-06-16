package com.example.trailers.utils

import okhttp3.ResponseBody

sealed class NetworkStatus<out T> {
    object Loading : NetworkStatus<Nothing>()
    data class Success<T>(val data: T?) : NetworkStatus<T>()
    data class Error<T>(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?,
    ) : NetworkStatus<T>()

    fun data() = if (this is Success) data else null
}