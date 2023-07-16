package com.sajjadio.trailers.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.sajjadio.trailers.domain.model.FavoriteMovie
import com.sajjadio.trailers.domain.repository.MovieRepository
import com.sajjadio.trailers.domain.utils.Resource
import com.sajjadio.trailers.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val movieRepo: MovieRepository
):ViewModel() ,FavoriteInteractListener{

    val responseSearchMovies:LiveData<List<FavoriteMovie>> = movieRepo.getAllSavedMovies().asLiveData()

    private val _clickItemEvent = MutableLiveData<Event<Int>>()
    val clickItemEvent: LiveData<Event<Int>> = _clickItemEvent
    private var _videoUrl = MutableLiveData<Event<String?>>()
    val videoUrl: LiveData<Event<String?>> = _videoUrl

    private fun getTrailerOfMovie(id: Int) {
        viewModelScope.launch {
            movieRepo.getMovieTrailer(id).collect { status ->
                when (status) {
                    is Resource.Success -> {
                        status.data?.let {
                            _videoUrl.postValue(Event(it.first().key))
                        }
                    }

                    is Resource.Error -> {
                    }
                }
            }
        }
    }

    override fun onClickFavoriteButton(movieId:Int) {
        viewModelScope.launch {
            movieRepo.deleteMovie(movieId)
        }
    }

    override fun onClickWatchButton(id: Int) {
        getTrailerOfMovie(id)
    }

    override fun onClickItem(id: Int) {
        _clickItemEvent.postValue(Event(id))
    }

}