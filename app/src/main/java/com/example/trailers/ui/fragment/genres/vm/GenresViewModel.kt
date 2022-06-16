package com.example.trailers.ui.fragment.genres.vm

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.trailers.data.model.genre.Genres
import com.example.trailers.data.model.movie.genremovie.MovieResult
import com.example.trailers.data.repository.genres.GenresRepo
import com.example.trailers.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GenresViewModel @Inject constructor(
    private val genresRepo: GenresRepo,
) : BaseViewModel() {

    private val _getGenresMovie: MutableLiveData<List<Genres>> = MutableLiveData()
    var getGenresMovie: LiveData<List<Genres>> = _getGenresMovie

    lateinit var getGenresOfMovie: LiveData<PagingData<MovieResult>>

    init {

    }

    fun checkConnection(isConnection: Boolean?) {
        if (isConnection == true) {
            viewModelScope.launch {
                genresRepo.getGenresMovie().collect {
                        it.data()?.genres?.let {
                            _getGenresMovie.postValue(it)
                    }
                }
            }
        }
    }

    fun getGenresOfMovie(genreId: String) {
        getGenresOfMovie =
            genresRepo
                .getGenreList(genreId = genreId)
                .cachedIn(viewModelScope + Dispatchers.Main)
                .asLiveData()
    }
}