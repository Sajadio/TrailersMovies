package com.sajjadio.trailers.ui.genres.viewModel

import androidx.lifecycle.*
import com.sajjadio.trailers.domain.model.GenresOfMovie
import com.sajjadio.trailers.domain.repository.MovieRepository
import com.sajjadio.trailers.ui.genres.GenresInteractListener
import com.sajjadio.trailers.utils.Event
import com.sajjadio.trailers.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(
    private val movieRepo: MovieRepository,
) : ViewModel(), GenresInteractListener {

    private val _responseGenresOfMovie: MutableLiveData<Resource<List<GenresOfMovie>>> =
        MutableLiveData()
    var responseGenresOfMovie: LiveData<Resource<List<GenresOfMovie>>> = _responseGenresOfMovie

    private val _clickItemEvent = MutableLiveData<Event<Pair<String,Int>>>()
    val clickItemEvent: LiveData<Event<Pair<String,Int>>> = _clickItemEvent

    init {
        loadGenresData()
    }

    private fun loadGenresData() {
        viewModelScope.launch {
            movieRepo.getGenresMovie().collect { state ->
                _responseGenresOfMovie.postValue(state)
            }
        }
    }

    override fun onClickGenreItem(title: String, id: Int) {
        _clickItemEvent.postValue(Event(Pair(title,id)))
    }

    override fun onClickItem(id:Int) {

    }
}