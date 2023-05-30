package com.sajjadio.trailers.ui.genres

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sajjadio.trailers.data.model.genre.GenresDto
import com.sajjadio.trailers.data.model.movie.genremovie.MovieResult
import com.sajjadio.trailers.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(
    private val movieRepo: MovieRepository,
) : ViewModel() {

    private val _listGenresMovieDto: MutableLiveData<List<GenresDto>> = MutableLiveData()
    var listGenresMovieDto: LiveData<List<GenresDto>> = _listGenresMovieDto

    private var _responseListOfMovie: MutableLiveData<PagingData<MovieResult>> = MutableLiveData()
    var responseListOfMovie: LiveData<PagingData<MovieResult>> = _responseListOfMovie

    val saveCurrentPosition = MutableLiveData<Int>()

    init {
        viewModelScope.launch {
            movieRepo.getGenresMovie().collect { state ->
                state.data()?.genres?.let { data ->
                    _listGenresMovieDto.postValue(data)
                }
            }
        }
    }

    fun getID(title: String) {
        viewModelScope.launch {
            movieRepo.getGenresMovie().collect { state ->
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
                movieRepo
                    .getGenreList(genreId = id)
                    .cachedIn(viewModelScope).collect { data ->
                        _responseListOfMovie.postValue(data)
                    }
            }
        }
    }

}