package com.sajjadio.trailers.ui.fragment.similar.viewModel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sajjadio.trailers.data.model.movie.similar.Result
import com.sajjadio.trailers.data.repository.similar.SimilarRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class SimilarViewModel @Inject constructor(
    private val repoImpl: SimilarRepoImpl,
) : ViewModel() {

    private val _listSimilarOfMovie = MutableLiveData<PagingData<Result>>()
    val listSimilarOfMovie: LiveData<PagingData<Result>> get() = _listSimilarOfMovie

    fun getSimilarOfMovieByID(id: Int) {
        viewModelScope.launch {
            repoImpl.listSimilarOfMovie(id).cachedIn(viewModelScope + Dispatchers.Main).collect {
                _listSimilarOfMovie.postValue(it)
            }
        }
    }
}