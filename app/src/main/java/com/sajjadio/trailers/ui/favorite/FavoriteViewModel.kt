package com.sajjadio.trailers.ui.favorite

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.sajjadio.trailers.domain.model.CommonResult
import com.sajjadio.trailers.domain.model.MovieDetails
import com.sajjadio.trailers.domain.repository.MovieRepository
import com.sajjadio.trailers.ui.base.BaseInteractListener
import com.sajjadio.trailers.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: MovieRepository
):ViewModel() ,FavoriteInteractListener{

    val responseSearchMovies:LiveData<List<MovieDetails>> = repository.getAllSavedMovies().asLiveData()

    private val _clickItemEvent = MutableLiveData<Event<Int>>()
    val clickItemEvent: LiveData<Event<Int>> = _clickItemEvent

    override fun onClickFavoriteButton(movieDetails: MovieDetails) {
        viewModelScope.launch {
            repository.deleteMovie(movieDetails)
        }
    }

    override fun onClickWatchButton(url: String) {
        Timber.d(url)
    }

    override fun onClickItem(id: Int) {
        _clickItemEvent.postValue(Event(id))
    }

}