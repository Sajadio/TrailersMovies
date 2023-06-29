package com.sajjadio.trailers.ui.search

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.sajjadio.trailers.data.model.movie.search.SearchResult
import com.sajjadio.trailers.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieRepo: MovieRepository
) : ViewModel() {

    val responseSearchMovies = MutableLiveData<PagingData<SearchResult>?>()

    fun onSearchInputChanged(text: CharSequence?) {
        if (isSearchInputValid(text.toString())) {
            getSearch(text.toString().trim())
        }
    }

    private fun getSearch(query: String) {
        viewModelScope.launch(Dispatchers.Main){
            movieRepo
                .getMovieSearch(query)
                .cachedIn(this)
                .collect { data ->
                    responseSearchMovies.postValue(data)
                }
        }
    }

    private fun isSearchInputValid(text: String) = text.isNotBlank()
}