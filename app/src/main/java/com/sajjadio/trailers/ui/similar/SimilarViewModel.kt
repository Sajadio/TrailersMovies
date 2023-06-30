package com.sajjadio.trailers.ui.similar

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sajjadio.trailers.domain.model.CommonResult
import com.sajjadio.trailers.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class SimilarViewModel @Inject constructor(
    movieRepo: MovieRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val movieId: Int = checkNotNull(savedStateHandle["movieId"])
    val similarOfMovie: LiveData<PagingData<CommonResult>> =
        movieRepo
            .getSimilarOfMovie(movieId)
            .cachedIn(viewModelScope + Dispatchers.Main)
            .asLiveData()
}