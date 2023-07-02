package com.sajjadio.trailers.ui.persons

import androidx.lifecycle.*
import com.sajjadio.trailers.domain.model.Cast
import com.sajjadio.trailers.domain.repository.MovieRepository
import com.sajjadio.trailers.utils.Event
import com.sajjadio.trailers.utils.NetworkStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(
    private val movieRepo: MovieRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(), PersonInteractListener {

    private val movieId: Int = checkNotNull(savedStateHandle["movieId"])

    private val _responsePersonsOfMovie = MutableLiveData<NetworkStatus<List<Cast>>>()
    val responsePersonsOfMovie: LiveData<NetworkStatus<List<Cast>>> = _responsePersonsOfMovie

    private val _clickItemEvent = MutableLiveData<Event<Int>>()
    val clickItemEvent: LiveData<Event<Int>> = _clickItemEvent

    init {
        loadPersonsData(movieId)
    }

    private fun loadPersonsData(movieId: Int) {
        viewModelScope.launch {
            movieRepo.getPersonOfMovieById(movieId).collect { status ->
                when (status) {
                    NetworkStatus.Loading -> _responsePersonsOfMovie.postValue(NetworkStatus.Loading)
                    is NetworkStatus.Success ->
                        _responsePersonsOfMovie.postValue(NetworkStatus.Success(status.data))

                    is NetworkStatus.Error ->
                        _responsePersonsOfMovie.postValue(NetworkStatus.Error(status.errorMessage))
                }
            }
        }
    }

    override fun onClickItem(id: Int) {
        _clickItemEvent.postValue(Event(id))
    }
}