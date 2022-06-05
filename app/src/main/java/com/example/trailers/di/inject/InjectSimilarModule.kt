package com.example.trailers.di.inject

import com.example.trailers.di.module.ProvideViewModel
import com.example.trailers.ui.fragment.movie.SimilarFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/* Install module responsible for providing ViewModel into parent component */
@Module(includes = [ProvideViewModel::class])
abstract class InjectSimilarModule {

    /* Install module into subcomponent to have access to bound fragment instance */
    @ContributesAndroidInjector(modules = [
        InjectViewModel::class
    ])
    abstract fun bind(): SimilarFragment

}