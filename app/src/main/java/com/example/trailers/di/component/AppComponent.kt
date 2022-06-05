package com.example.trailers.di.component

import android.content.Context
import com.example.trailers.App
import com.example.trailers.di.inject.*
import com.example.trailers.di.module.AppModule
import com.example.trailers.di.module.NetworkModule
import com.example.trailers.di.module.ProvideViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [
    InjectActivityModule::class,
    InjectHomeModule::class,
    InjectSearchModule::class,
    InjectCommonModule::class,
    InjectGenreModule::class,
    InjectMovieDetailsModule::class,
    InjectSimilarModule::class,
    AppModule::class,
    AndroidSupportInjectionModule::class,
    NetworkModule::class,
    ProvideViewModel::class])
interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(@Named("my_application")app: App): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}