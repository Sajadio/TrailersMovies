package com.example.trailers.di.component

import android.content.Context
import com.example.trailers.App
import com.example.trailers.di.inject.*
import com.example.trailers.di.factory.FactoryModule
import com.example.trailers.di.module.NetworkModule
import com.example.trailers.di.module.ViewModelProvide
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [
    InjectHomeActivity::class,
    InjectHomeFragment::class,
    InjectCommonFragment::class,
    InjectSimilarFragment::class,
    InjectSearchFragment::class,
    InjectActorsFragment::class,
    InjectMovieDetailsFragment::class,
    InjectBottomSheet::class,
    InjectGenresFragment::class,
    FactoryModule::class,
    NetworkModule::class,
    AndroidSupportInjectionModule::class,
    ViewModelProvide::class])
interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(@Named("my_application") app: App): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}