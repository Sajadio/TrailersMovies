package com.example.trailers.ui.fragment.search.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.trailers.data.model.genre.Genre
import com.example.trailers.data.model.search.Result
import com.example.trailers.data.repository.search.SearchRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchViewModel @Inject constructor(private val searchRepo: SearchRepo) : ViewModel() {

    private val _getMoviesSearch: MutableLiveData<PagingData<Result>> = MutableLiveData()
    val getMoviesSearch: LiveData<PagingData<Result>> = _getMoviesSearch

    private val _genres: MutableLiveData<List<Genre>?> =
        MutableLiveData()
    val genres: LiveData<List<Genre>?> = _genres

    fun getSearch(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            searchRepo.getMultiSearch(query = query).cachedIn(viewModelScope).collect { data ->
                _getMoviesSearch.postValue(data)
            }
        }
    }

}