package com.sajjadio.trailers.utils


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

interface SafeApiCall {

    fun <T> safeApiCall(response: suspend () -> Response<T>): Flow<NetworkStatus<T?>> {
        return flow {
            emit(NetworkStatus.Loading)
            try {
                emit(checkIsSuccessfulResponse(response.invoke()))
            } catch (e:Exception) {
                emit(NetworkStatus.Error(e.message))
            }
        }
    }

    private fun <T> checkIsSuccessfulResponse(response: Response<T>): NetworkStatus<T> =
        if (response.isSuccessful) NetworkStatus.Success(response.body()) else NetworkStatus.Error(
            response.message())
}