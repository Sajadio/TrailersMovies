//package com.example.trailers.utils
//
//import kotlinx.coroutines.flow.*
//import retrofit2.HttpException
//
// inline fun <ResultType, RequestType> networkBoundResource(
//     crossinline query: () -> Flow<ResultType>,
//     crossinline fetch: suspend () -> RequestType,
//     crossinline saveFetchResult: suspend (RequestType) -> Unit,
//     crossinline shouldFetch: (ResultType) -> Boolean = { true },
//) = flow {
//    val data = query().first()
//    val flow = if (shouldFetch(data)) {
//        emit(NetworkStatus.Loading)
//        try {
//            saveFetchResult(fetch())
//            query().map { NetworkStatus.Success(it) }
//        } catch (throwable: HttpException) {
//            query().map {
//                NetworkStatus.Error(throwable )
//            }
//        }
//    } else {
//        query().map { NetworkStatus.Success(it) }
//    }
//    emitAll(flow)
//}