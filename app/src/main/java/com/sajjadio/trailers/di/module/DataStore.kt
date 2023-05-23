package com.sajjadio.trailers.di.module

import com.sajjadio.trailers.data.storage.DataStorage
import com.sajjadio.trailers.data.storage.DataStorageImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface DataStore {

    @Binds
    abstract fun bind(
       dataStorageImp: DataStorageImp
    ): DataStorage
}