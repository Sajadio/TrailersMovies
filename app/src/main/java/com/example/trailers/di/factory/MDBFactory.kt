package com.example.trailers.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.trailers.ui.vm.MDBViewModel
import javax.inject.Provider

/* MDBFactory for creating MDBViewModel instances */

class MDBFactory(private val provider: Provider<MDBViewModel>): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return provider.get() as T
    }

}