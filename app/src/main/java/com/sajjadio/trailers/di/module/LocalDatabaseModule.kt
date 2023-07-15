package com.sajjadio.trailers.di.module

import android.content.Context
import androidx.room.Room
import com.sajjadio.trailers.data.dataSource.local.AppDatabase
import com.sajjadio.trailers.data.dataSource.local.dao.MovieDao
import com.sajjadio.trailers.data.dataSource.local.dao.FavoriteMovieDao
import com.sajjadio.trailers.data.dataSource.local.dao.SearchMovieRemoteKeyDao
import com.sajjadio.trailers.utils.Constant.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocalDatabaseModule {


    @Provides
    @Singleton
    fun provideLocalRoomDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context = context.applicationContext,
            AppDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideFavoriteMovieDao(database: AppDatabase): FavoriteMovieDao {
        return database.getFavoriteMovieDao()
    }

    @Provides
    @Singleton
    fun provideMovieDao(database: AppDatabase): MovieDao {
        return database.getMovieDao()
    }

    @Provides
    @Singleton
    fun provideSearchMovieDao(database: AppDatabase): SearchMovieRemoteKeyDao {
        return database.getSearchMovieRemoteKeyDao()
    }
}