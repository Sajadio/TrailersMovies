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
import com.sajjadio.trailers.domain.utils.Resource
import com.sajjadio.trailers.ui.base.BaseInteractListener
import com.sajjadio.trailers.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val movieRepo: MovieRepository
):ViewModel() ,FavoriteInteractListener{

    val responseSearchMovies:LiveData<List<MovieDetails>> = movieRepo.getAllSavedMovies().asLiveData()

    private val _clickItemEvent = MutableLiveData<Event<Int>>()
    val clickItemEvent: LiveData<Event<Int>> = _clickItemEvent
    private var _videoUrl = MutableLiveData<String?>()
    val videoUrl: LiveData<String?> = _videoUrl

    private fun getTrailerOfMovie(id: Int) {
        viewModelScope.launch {
            movieRepo.getMovieTrailer(id).collect { status ->
                when (status) {
                    is Resource.Success -> {
                        status.data?.let {
                            _videoUrl.postValue(it.first().key)
                        }
                    }

                    is Resource.Error -> {
                    }
                }
            }
        }
    }

    override fun onClickFavoriteButton(movieDetails: MovieDetails) {
        viewModelScope.launch {
            movieRepo.deleteMovie(movieDetails)
        }
    }

    override fun onClickWatchButton(id: Int) {
        getTrailerOfMovie(id)
    }

    override fun onClickItem(id: Int) {
        _clickItemEvent.postValue(Event(id))
    }

}