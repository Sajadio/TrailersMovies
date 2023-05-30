package com.sajjadio.trailers.ui.similar

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sajjadio.trailers.data.model.movie.similar.SimilarResult
import com.sajjadio.trailers.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class SimilarViewModel @Inject constructor(
    private val movieRepo: MovieRepository,
) : ViewModel() {

    private val _listSimilarOfMovie = MutableLiveData<PagingData<SimilarResult>>()
    val listSimilarOfMovie: LiveData<PagingData<SimilarResult>> = _listSimilarOfMovie

    fun getSimilarOfMovieByID(id: Int) {
        viewModelScope.launch {
            movieRepo.listSimilarOfMovie(id).cachedIn(viewModelScope + Dispatchers.Main).collect {
                _listSimilarOfMovie.postValue(it)
            }
        }
    }
}