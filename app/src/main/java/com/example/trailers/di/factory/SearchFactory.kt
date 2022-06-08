package com.example.trailers.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.trailers.ui.fragment.home.vm.HomeViewModel
import com.example.trailers.ui.fragment.search.vm.SearchViewModel
import javax.inject.Provider

/* HomeFactory for creating HomeViewModel instances */

class SearchFactory(private val provider: Provider<SearchViewModel>): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return provider.get() as T
    }

}