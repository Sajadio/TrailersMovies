package com.example.trailers.di.module

import androidx.room.Room
import com.example.trailers.App
import com.example.trailers.data.loacal.TrailersDatabase
import com.example.trailers.data.loacal.coming.ComingDao
import com.example.trailers.data.loacal.popular.PopularDao
import com.example.trailers.data.loacal.rated.RatedDao
import com.example.trailers.data.loacal.trend.TrendDao
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class LocalModule {

    @Singleton
    @Provides
    fun provideApplication(@Named("my_application") application: App) = application

    @Singleton
    @Provides
    fun providesRoomDatabase(application: App) =
        Room.databaseBuilder(application, TrailersDatabase::class.java, "trailers_db.db").build()

    @Singleton
    @Provides
    fun providesProductPopularDao(trailersDatabase: TrailersDatabase): PopularDao {
        return trailersDatabase.getPopularDao()
    }

    @Singleton
    @Provides
    fun providesProductTrendDao(trailersDatabase: TrailersDatabase): TrendDao {
        return trailersDatabase.getTrendDao()
    }

    @Singleton
    @Provides
    fun providesProductRatedDao(trailersDatabase: TrailersDatabase): RatedDao {
        return trailersDatabase.getRatedDao()
    }

    @Singleton
    @Provides
    fun providesProductComingDao(trailersDatabase: TrailersDatabase): ComingDao {
        return trailersDatabase.getComingDao()
    }

}