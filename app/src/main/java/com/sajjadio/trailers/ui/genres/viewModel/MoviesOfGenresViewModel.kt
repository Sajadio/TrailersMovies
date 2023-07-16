package com.sajjadio.trailers.ui.genres.viewModel

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.sajjadio.trailers.domain.model.CommonResult
import com.sajjadio.trailers.domain.repository.MovieRepository
import com.sajjadio.trailers.ui.base.BaseInteractListener
import com.sajjadio.trailers.utils.Event
import com.sajjadio.trailers.utils.language
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesOfGenresViewModel @Inject constructor(
    private val movieRepo: MovieRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(), BaseInteractListener {

    private var _responseListOfMovie: MutableLiveData<PagingData<CommonResult>> = MutableLiveData()
    var responseListOfMovie: LiveData<PagingData<CommonResult>> = _responseListOfMovie

    private val _clickItemEvent = MutableLiveData<Event<Int>>()
    val clickItemEvent: LiveData<Event<Int>> = _clickItemEvent

    val titleToolBar: String = checkNotNull(savedStateHandle["genreTitle"])
    private val genreId: Int = checkNotNull(savedStateHandle["genreId"])

    init {
        getGenresOfMovie(genreId)
    }

    private fun getGenresOfMovie(genreId: Int) {
        viewModelScope.launch {
            genreId.let { id ->
                movieRepo
                    .getMoviesOfGenreById(genreId = id, language())
                    .cachedIn(viewModelScope).collect { data ->
                        _responseListOfMovie.postValue(data)
                    }
            }
        }
    }


    override fun onClickItem(id: Int) {
        _clickItemEvent.postValue(Event(id))
    }
}