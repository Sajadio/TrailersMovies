package com.example.trailers.ui.fragment.search.vm

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.trailers.R
import com.example.trailers.data.model.genre.Genre
import com.example.trailers.data.model.movie.search.Result
import com.example.trailers.data.repository.search.SearchRepo
import com.example.trailers.ui.base.BaseViewModel
import com.example.trailers.utils.NetworkStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

class SearchViewModel @Inject constructor(private val searchRepo: SearchRepo) : BaseViewModel() {

    private val _getMoviesSearch: MutableLiveData<PagingData<Result>?> = MutableLiveData()
    val getMoviesSearch: LiveData<PagingData<Result>?> = _getMoviesSearch

    fun getSearch(query: String) {
        viewModelScope.launch {
            searchRepo.getMovieSearch(query = query).cachedIn(this).collect { data ->
                    _getMoviesSearch.postValue(data)
            }
        }
    }

}