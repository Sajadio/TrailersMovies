package com.example.trailers.utils


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response

interface SafeApiCall {
    fun <T> safeApiCall(response: Response<T>): Flow<NetworkStatus<T>> {
        return flow {
            try {
                emit(NetworkStatus.Loading)
                if (response.isSuccessful)
                    emit(NetworkStatus.Success(response.body()))
                else
                    emit(
                        NetworkStatus.Error(
                            isNetworkError = false,
                            errorCode = response.code(),
                            errorBody = response.errorBody()
                        )
                    )
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