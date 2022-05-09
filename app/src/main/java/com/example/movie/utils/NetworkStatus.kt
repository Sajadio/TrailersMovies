package com.example.movie.utils

sealed class NetworkStatus<out T> {
    object Loading : NetworkStatus<Nothing>()
    data class Success<out T>(val data: T?) : NetworkStatus<T>()
    data class Error(val error: Exception) : NetworkStatus<Nothing>()

    fun data() = if (this is Success) data else null
}