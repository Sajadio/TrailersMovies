package com.sajjadio.trailers.ui.search

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sajjadio.trailers.data.model.movie.search.SearchResult
import com.sajjadio.trailers.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieRepo: MovieRepository
) : ViewModel() {

    val getMoviesSearch = MutableLiveData<PagingData<SearchResult>?>()

    private val query: MutableLiveData<PagingData<SearchResult>?> = MutableLiveData()

    fun getSearch(query: String?) {
        viewModelScope.launch {
            movieRepo.getMovieSearch(query = query).cachedIn(this).collect { data ->
                getMoviesSearch.postValue(data)
            }
        }
    }

}