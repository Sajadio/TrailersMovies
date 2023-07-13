package com.sajjadio.trailers.di.module

import com.sajjadio.trailers.BuildConfig
import com.sajjadio.trailers.data.dataSource.remote.AuthInterceptor
import com.sajjadio.trailers.data.dataSource.remote.MovieApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providerRetrofit(retrofit: Retrofit): MovieApiService {
        return retrofit.create(MovieApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        @Named("Movie_Base_Url") baseUrl: String,
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit
            .Builder()
            .apply {
                baseUrl(baseUrl)
                addConverterFactory(gsonConverterFactory)
                client(okHttpClient)
            }.build()
    }

    @Provides
    @Singleton
    @Named("Movie_Base_Url")
    fun provideBaseUrlOfMovie(): String = BuildConfig.BASE_URL

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
    ): OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(authInterceptor)
        readTimeout(100L, TimeUnit.SECONDS)
        connectTimeout(100L, TimeUnit.SECONDS)
        writeTimeout(100L, TimeUnit.SECONDS)
    }.build()

}