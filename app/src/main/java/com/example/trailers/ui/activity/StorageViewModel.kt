package com.example.trailers.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StorageViewModel : ViewModel() {

    private val _getThemeLiveData : MutableLiveData<Int> = MutableLiveData()
    val getThemeLiveData : LiveData<Int> =_getThemeLiveData

    fun saveTheme(theme:Int){
        _getThemeLiveData.postValue(theme)
    }

}