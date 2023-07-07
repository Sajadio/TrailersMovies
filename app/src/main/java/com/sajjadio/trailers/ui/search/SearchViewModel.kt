package com.sajjadio.trailers.ui.search

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sajjadio.trailers.data.model.movie.search.SearchResult
import com.sajjadio.trailers.domain.repository.MovieRepository
import com.sajjadio.trailers.ui.base.BaseInteractListener
import com.sajjadio.trailers.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieRepo: MovieRepository
) : ViewModel(), BaseInteractListener {

    val responseSearchMovies = MutableLiveData<PagingData<SearchResult>?>()
    private val _clickItemEvent = MutableLiveData<Event<Int>>()
    val clickItemEvent: LiveData<Event<Int>> = _clickItemEvent

    fun onSearchInputChanged(text: CharSequence?) {
        if (isSearchInputValid(text.toString())) {
            getSearch(text.toString().trim())
        }
    }

    private fun getSearch(query: String) {
        viewModelScope.launch(Dispatchers.Main) {
            movieRepo
                .getMovieSearch(query)
                .cachedIn(this)
                .collect { data ->
                    responseSearchMovies.postValue(data)
                }
        }
    }

    private fun isSearchInputValid(text: String) = text.isNotBlank()

    override fun onClickItem(id:Int) {
        _clickItemEvent.postValue(Event(id))
    }
}