package com.example.trailers.ui.fragment.home.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.trailers.R
import com.example.trailers.data.storage.DataStorage
import com.example.trailers.data.storage.DataStorageImp
import com.example.trailers.utils.ThemeHelper
import com.example.trailers.utils.UiMode
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StorageViewModel @Inject constructor(private val dataStorage: DataStorageImp) : ViewModel() {

    val selectedTheme = dataStorage.uiModeFlow.asLiveData()
    fun changeSelectedTheme(uiMode: UiMode) {
        viewModelScope.launch {
            when (uiMode) {
                UiMode.SYSTEM_DEFAULT -> dataStorage.setUIMode(uiMode)
                UiMode.LIGHT -> dataStorage.setUIMode(uiMode)
                UiMode.DARK -> dataStorage.setUIMode(uiMode)
            }

        }
    }

}