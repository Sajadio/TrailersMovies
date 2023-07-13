package com.sajjadio.trailers.domain.utils

sealed class Resource<out T>(val data: T? = null, val message: String? = null) {
    data class Success<T>(val item: T?) : Resource<T>(item)
    data class Error(val errorMessage: String?) : Resource<Nothing>(message = errorMessage)
}