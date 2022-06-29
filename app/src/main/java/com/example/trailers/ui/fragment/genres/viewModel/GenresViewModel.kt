package com.example.trailers.ui.fragment.genres.viewModel

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.trailers.data.model.genre.Genres
import com.example.trailers.data.model.movie.genremovie.MovieResult
import com.example.trailers.data.repository.genres.GenresRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(
    private val genresRepoImpl: GenresRepoImpl,
) : ViewModel() {

    private val _listGenresMovie: MutableLiveData<List<Genres>> = MutableLiveData()
    var listGenresMovie: LiveData<List<Genres>> = _listGenresMovie

    private var _responseListOfMovie: MutableLiveData<PagingData<MovieResult>> = MutableLiveData()
    var responseListOfMovie: LiveData<PagingData<MovieResult>> = _responseListOfMovie

    val saveCurrentPosition = MutableLiveData<Int>()

    init {
        viewModelScope.launch {
            genresRepoImpl.getGenresMovie().collect { state ->
                state.data()?.genres?.let { data ->
                    _listGenresMovie.postValue(data)
                }
            }
        }
    }

    fun getID(title: String) {
        viewModelScope.launch {
            genresRepoImpl.getGenresMovie().collect { state ->
                state.data()?.genres?.let { data ->
                    data.forEach {
                        if (it.name == title)
                            getGenresOfMovie(it.id.toString())
                    }
                }
            }
        }
    }


    private fun getGenresOfMovie(genreId: String?) {
        viewModelScope.launch {
            genreId?.let { id ->
                genresRepoImpl
                    .getGenreList(genreId = id)
                    .cachedIn(viewModelScope).collect { data ->
                        _responseListOfMovie.postValue(data)
                    }
            }
        }
    }

}