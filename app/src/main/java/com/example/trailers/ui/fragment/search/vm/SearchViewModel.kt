package com.example.trailers.ui.fragment.search.vm

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.trailers.data.model.genre.Genre
import com.example.trailers.data.model.movie.search.Result
import com.example.trailers.data.repository.search.SearchRepo
import com.example.trailers.utils.NetworkStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchViewModel @Inject constructor(private val searchRepo: SearchRepo) : ViewModel() {

    private val _getMoviesSearch: MutableLiveData<PagingData<Result>> = MutableLiveData()
    val getMoviesSearch: LiveData<PagingData<Result>> = _getMoviesSearch

    fun getSearch(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            searchRepo.getMovieSearch(query = query).cachedIn(this).collect { data ->
                _getMoviesSearch.postValue(data)
            }
        }
    }

}