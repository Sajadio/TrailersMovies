package com.sajjadio.trailers.ui.fragment.search.viewModel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sajjadio.trailers.data.model.movie.search.Result
import com.sajjadio.trailers.data.repository.search.SearchRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepoImpl: SearchRepoImpl) :
    ViewModel() {

    val _getMoviesSearch = MutableLiveData<PagingData<Result>?>()
    val getMoviesSearch: LiveData<PagingData<Result>?> = _getMoviesSearch

    private val query: MutableLiveData<PagingData<Result>?> = MutableLiveData()

    fun getSearch(query: String?) {
        viewModelScope.launch {
            searchRepoImpl.getMovieSearch(query = query).cachedIn(this).collect { data ->
                _getMoviesSearch.postValue(data)
            }
        }
    }

}