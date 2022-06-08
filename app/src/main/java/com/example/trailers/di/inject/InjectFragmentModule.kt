package com.example.trailers.di.inject

import com.example.trailers.di.annotate.FragmentScope
import com.example.trailers.di.module.ProvideViewModel
import com.example.trailers.ui.fragment.common.CommonFragment
import com.example.trailers.ui.fragment.genres.GenresFragment
import com.example.trailers.ui.fragment.home.HomeFragment
import com.example.trailers.ui.fragment.movie.MovieFragment
import com.example.trailers.ui.fragment.movie.SimilarFragment
import com.example.trailers.ui.fragment.search.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/* Install module responsible for providing ViewModel into parent component */
@Module(includes = [ProvideViewModel::class])
abstract class InjectFragmentModule {

    /* Install module into subcomponent to have access to bound fragment instance */
    @FragmentScope
    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun bindHome(): HomeFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun bindSearch(): SearchFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun bindCommon(): CommonFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun bindSimilar(): SimilarFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun bindMovieDetails(): MovieFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun bindGenres(): GenresFragment

}