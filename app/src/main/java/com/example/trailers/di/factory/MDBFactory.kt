package com.example.trailers.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.trailers.ui.fragment.home.vm.HomeViewModel
import javax.inject.Provider

/* MDBFactory for creating HomeViewModel instances */

class MDBFactory(private val provider: Provider<HomeViewModel>): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return provider.get() as T
    }

}