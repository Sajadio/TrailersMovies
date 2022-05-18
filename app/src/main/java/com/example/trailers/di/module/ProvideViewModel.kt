package com.example.trailers.di.module

import androidx.lifecycle.ViewModel
import com.example.trailers.data.repository.MDBRepo
import com.example.trailers.di.annotate.ViewModelKey
import com.example.trailers.data.repository.search.SearchRepo
import com.example.trailers.ui.fragment.search.vm.SearchViewModel
import com.example.trailers.ui.vm.MDBViewModel
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