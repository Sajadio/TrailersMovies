package com.example.movie.utils


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

interface SafeApiCall {
    fun <T> safeApiCall(apiCall: suspend () -> T): Flow<NetworkStatus<T>> {
        return flow {
            try {
                emit(NetworkStatus.Loading)
                emit(NetworkStatus.Success(apiCall.invoke()))
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException ->
                        emit(
                            NetworkStatus.Error(
                                isNetworkError = false,
                                errorCode = throwable.code(),
                                errorBody = throwable.response()?.errorBody()
                            )
                        )
                    else ->
                        emit(NetworkStatus.Error(true, null, null))
                }
            }
        }
    }
}