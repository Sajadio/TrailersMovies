package com.example.trailers.di.module

import androidx.lifecycle.ViewModel
import com.example.trailers.data.repository.home.HomeRepo
import com.example.trailers.di.annotate.ViewModelKey
import com.example.trailers.data.repository.common.CommonRepo
import com.example.trailers.data.repository.search.SearchRepo
import com.example.trailers.ui.fragment.search.vm.SearchViewModel
import com.example.trailers.ui.fragment.home.vm.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ProvideViewModel {

    @Provides
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun provideMDBViewModel(homeRepo: HomeRepo, commonRepo: CommonRepo): ViewModel =
        HomeViewModel(homeRepo,commonRepo)

    @Provides
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun provideSearchViewModel(searchRepo: SearchRepo): ViewModel =
        SearchViewModel(searchRepo)
}