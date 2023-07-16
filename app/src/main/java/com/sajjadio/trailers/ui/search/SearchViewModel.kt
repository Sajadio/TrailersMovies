package com.sajjadio.trailers.ui.search

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sajjadio.trailers.domain.model.SearchMovieResult
import com.sajjadio.trailers.domain.repository.MovieRepository
import com.sajjadio.trailers.ui.base.BaseInteractListener
import com.sajjadio.trailers.utils.Event
import com.sajjadio.trailers.utils.language
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieRepo: MovieRepository
) : ViewModel(), BaseInteractListener {

    var responseSearchMovies = MutableLiveData<PagingData<SearchMovieResult>?>()
    private val _clickItemEvent = MutableLiveData<Event<Int>>()
    val clickItemEvent: LiveData<Event<Int>> = _clickItemEvent

    var isSearchQueryEmpty = MutableLiveData(true)


    fun onSearchInputChanged(text: CharSequence?) {
        val query = text.toString().trim()
        isSearchQueryEmpty.postValue(!isSearchInputValid(query))
        if (isSearchInputValid(query)) {
            getSearch(query)
        } else {
            responseSearchMovies.value = PagingData.empty()
        }
    }

    @OptIn(FlowPreview::class)
    private fun getSearch(query: String) {
        viewModelScope.launch(Dispatchers.Main) {
            movieRepo
                .getMovieSearchByQuery(query, language())
                .debounce(500L)
                .cachedIn(viewModelScope + Dispatchers.Main)
                .distinctUntilChanged()
                .collect {
                    responseSearchMovies.postValue(it)
                }
        }
    }

    private fun isSearchInputValid(text: String) = text.isNotBlank()

    override fun onClickItem(id: Int) {
        _clickItemEvent.postValue(Event(id))
    }
}