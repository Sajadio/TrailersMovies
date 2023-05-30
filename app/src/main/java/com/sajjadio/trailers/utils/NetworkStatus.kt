package com.sajjadio.trailers.utils

sealed class NetworkStatus<out T>(val data: T? = null, val message: String? = null) {
    object Loading : NetworkStatus<Nothing>(null)
    data class Success<T>(val item: T?) : NetworkStatus<T>(item)
    data class Error(val errorMessage: String?) : NetworkStatus<Nothing>(message = errorMessage)
}