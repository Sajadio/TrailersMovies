package com.sajjadio.trailers.ui.persons

import androidx.lifecycle.*
import com.sajjadio.trailers.domain.model.Cast
import com.sajjadio.trailers.domain.repository.MovieRepository
import com.sajjadio.trailers.utils.Event
import com.sajjadio.trailers.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(
    private val movieRepo: MovieRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(), PersonInteractListener {

    private val movieId: Int = checkNotNull(savedStateHandle["movieId"])

    private val _responsePersonsOfMovie = MutableLiveData<Resource<List<Cast>>>()
    val responsePersonsOfMovie: LiveData<Resource<List<Cast>>> = _responsePersonsOfMovie

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _clickItemEvent = MutableLiveData<Event<Int>>()
    val clickItemEvent: LiveData<Event<Int>> = _clickItemEvent

    init {
        loadPersonsData(movieId)
    }

    private fun loadPersonsData(movieId: Int) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            movieRepo.getPersonsOfMovieById(movieId).collect { status ->
                when (status) {
                    is Resource.Success -> {
                        _isLoading.postValue(false)
                        _responsePersonsOfMovie.postValue(Resource.Success(status.data))
                    }

                    is Resource.Error ->{
                        _isLoading.postValue(false)
                        _responsePersonsOfMovie.postValue(Resource.Error(status.errorMessage))
                    }
                }
            }
        }
    }

    override fun onClickItem(id: Int) {
        _clickItemEvent.postValue(Event(id))
    }
}