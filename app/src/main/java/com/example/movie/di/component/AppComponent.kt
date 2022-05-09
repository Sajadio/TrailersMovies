package com.example.movie.di.component

import android.content.Context
import com.example.movie.App
import com.example.movie.di.module.AppModule
import com.example.movie.di.inject.InjectHomeModule
import com.example.movie.di.inject.InjectSearchModule
import com.example.movie.di.module.NetworkModule
import com.example.movie.di.module.ProvideViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    InjectHomeModule::class,
    InjectSearchModule::class,
    AppModule::class,
    AndroidSupportInjectionModule::class,
    NetworkModule::class,
    ProvideViewModel::class])
interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: App): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}