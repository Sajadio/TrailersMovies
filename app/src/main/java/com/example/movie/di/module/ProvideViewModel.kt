package com.example.movie.di.module

import androidx.lifecycle.ViewModel
import com.example.movie.data.repository.MDBRepo
import com.example.movie.di.annotate.ViewModelKey
import com.example.movie.data.repository.search.SearchRepo
import com.example.movie.ui.fragment.search.vm.SearchViewModel
import com.example.movie.ui.vm.MDBViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ProvideViewModel {

    @Provides
    @IntoMap
    @ViewModelKey(MDBViewModel::class)
    fun provideMDBViewModel(mdbRepo: MDBRepo): ViewModel =
        MDBViewModel(mdbRepo)

    @Provides
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun provideSearchViewModel(searchRepo: SearchRepo): ViewModel =
        SearchViewModel(searchRepo)
}