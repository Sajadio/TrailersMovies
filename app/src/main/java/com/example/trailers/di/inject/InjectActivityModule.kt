package com.example.trailers.di.inject

import com.example.trailers.di.module.ProvideViewModel
import com.example.trailers.ui.activity.MovieActivity
import com.example.trailers.ui.fragment.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/* Install module responsible for providing ViewModel into parent component */
@Module(includes = [ProvideViewModel::class])
abstract class InjectActivityModule {

    /* Install module into subcomponent to have access to bound fragment instance */
    @ContributesAndroidInjector(modules = [
        InjectViewModel::class
    ])
    abstract fun bind(): MovieActivity

}