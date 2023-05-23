package com.sajjadio.trailers.ui.fragment.home.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.sajjadio.trailers.data.storage.DataStorageImp
import com.sajjadio.trailers.utils.UiMode
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