package com.example.movie.ui.fragment.search.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movie.data.model.search.Result
import com.example.movie.data.repository.search.SearchRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchViewModel @Inject constructor(private val searchRepo: SearchRepo) : ViewModel() {

    private val _getMoviesSearch: MutableLiveData<PagingData<Result>> = MutableLiveData()
    val getMoviesSearch: LiveData<PagingData<Result>> = _getMoviesSearch

    fun getSearch(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            searchRepo.getMultiSearch(query = query).cachedIn(viewModelScope).collect {
                _getMoviesSearch.postValue(it)
            }
        }
    }
}