package com.example.trailers.di.module

import com.example.trailers.data.repository.home.HomeRepo
import com.example.trailers.di.annotate.ViewModelKey
import com.example.trailers.data.repository.common.CommonRepo
import com.example.trailers.data.repository.genres.GenresRepo
import com.example.trailers.data.repository.search.SearchRepo
import com.example.trailers.data.storage.DataStorageImp
import com.example.trailers.ui.base.BaseViewModel
import com.example.trailers.ui.fragment.common.vm.CommonViewModel
import com.example.trailers.ui.fragment.genres.vm.GenresViewModel
import com.example.trailers.ui.fragment.search.vm.SearchViewModel
import com.example.trailers.ui.fragment.home.vm.HomeViewModel
import com.example.trailers.ui.fragment.home.vm.StorageViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ViewModelProvide {

    @Provides
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun provideMDBViewModel(homeRepo: HomeRepo): BaseViewModel =
        HomeViewModel(homeRepo)

    @Provides
    @IntoMap
    @ViewModelKey(CommonViewModel::class)
    fun provideCommonViewModel(commonRepo: CommonRepo): BaseViewModel =
        CommonViewModel(commonRepo)

    @Provides
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun provideSearchViewModel(searchRepo: SearchRepo): BaseViewModel =
        SearchViewModel(searchRepo)

    @Provides
    @IntoMap
    @ViewModelKey(GenresViewModel::class)
    fun provideGenresViewModel(genresRepo: GenresRepo): BaseViewModel =
        GenresViewModel(genresRepo)

    @Provides
    @IntoMap
    @ViewModelKey(StorageViewModel::class)
    fun provideStorageViewModel(dataStorage: DataStorageImp): BaseViewModel =
        StorageViewModel(dataStorage)

}