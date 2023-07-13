package com.sajjadio.trailers.data.dataSource.remote

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton
import com.sajjadio.trailers.BuildConfig

@Singleton
class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val httpUrl = request.url.newBuilder()
            .addQueryParameter(API_KEY, BuildConfig.API_KEY)
            .build()
        request = request.newBuilder().url(httpUrl).build()
        return chain.proceed(request)
    }

    private companion object {
        const val API_KEY = "api_key"
    }
}