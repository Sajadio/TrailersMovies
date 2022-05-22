package com.example.trailers.ui.fragment.movie.vm

import androidx.lifecycle.*
import com.example.trailers.data.model.movie.actors.Actors
import com.example.trailers.data.model.movie.id.MovieID
import com.example.trailers.data.model.movie.similar.Similar
import com.example.trailers.data.repository.MovieRepo
import com.example.trailers.utils.NetworkStatus
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieViewModel @Inject constructor(private val repo: MovieRepo) : ViewModel() {

    private val _responseData: MutableLiveData<NetworkStatus<MovieID>> = MutableLiveData()
    val responseData: LiveData<NetworkStatus<MovieID>> = _responseData

    private val _actors: MutableLiveData<NetworkStatus<Actors>> = MutableLiveData()
    val actors: LiveData<NetworkStatus<Actors>> = _actors

    private val _similar: MutableLiveData<NetworkStatus<Similar>> = MutableLiveData()
    val similar: LiveData<NetworkStatus<Similar>> = _similar

    fun getID(id: Int) {
        viewModelScope.launch {
            repo.getMoviesDetails(id).collect {
                _responseData.postValue(it)
                it.data()?.let {
                    getActors(it.id)
                    getSimilar(it.id)
                }
            }
        }
    }

    private fun getActors(id: Int) {
        viewModelScope.launch {
            repo.getActors(id).collect {
                _actors.postValue(it)
            }
        }
    }

    private fun getSimilar(id: Int) {
        viewModelScope.launch {
            repo.getSimilar(id).collect {
                _similar.postValue(it)
            }
        }
    }

}