package com.example.trailers.ui.fragment.home.viewModel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.trailers.data.storage.DataStorageImp
import com.example.trailers.utils.Constant.TAG
import com.example.trailers.utils.UiMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StorageViewModel @Inject constructor(private val dataStorage: DataStorageImp) : ViewModel() {

    val selectedTheme = dataStorage.uiModeFlow.asLiveData()

    fun changeSelectedTheme(uiMode: UiMode) {
        viewModelScope.launch {
            when (uiMode) {
                UiMode.SYSTEM_DEFAULT -> {
                    dataStorage.setUIMode(uiMode)
                }
                UiMode.LIGHT -> {
                    dataStorage.setUIMode(uiMode)
                }
                UiMode.DARK -> {
                    dataStorage.setUIMode(uiMode)
                }
            }

        }
    }
}