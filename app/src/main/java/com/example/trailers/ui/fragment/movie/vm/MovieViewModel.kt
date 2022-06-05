package com.example.trailers.ui.fragment.movie.vm

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.trailers.data.model.movie.actors.Actors
import com.example.trailers.data.model.movie.id.IDMovie
import com.example.trailers.data.model.movie.similar.Result
import com.example.trailers.data.model.movie.similar.Similar
import com.example.trailers.data.repository.movie.MovieRepo
import com.example.trailers.data.model.movie.video.VideoMovie
import com.example.trailers.utils.NetworkStatus
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieViewModel @Inject constructor(private val repo: MovieRepo) : ViewModel() {

    private val _responseData: MutableLiveData<NetworkStatus<IDMovie>> = MutableLiveData()
    val responseData: LiveData<NetworkStatus<IDMovie>> = _responseData

    private val _actors: MutableLiveData<NetworkStatus<Actors>> = MutableLiveData()
    val actors: LiveData<NetworkStatus<Actors>> = _actors

    private val _similar: MutableLiveData<NetworkStatus<Similar>> = MutableLiveData()
    val similar: LiveData<NetworkStatus<Similar>> = _similar

    private val _playVideo: MutableLiveData<NetworkStatus<VideoMovie>> = MutableLiveData()
    val playVideo: LiveData<NetworkStatus<VideoMovie>> = _playVideo

    lateinit var allSimilar: LiveData<PagingData<Result>>


    fun getID(id: Int) {
        viewModelScope.launch {
            repo.getMoviesDetails(id).collect {
                _responseData.postValue(it)

                it.data()?.let {
                    getActors(it.id)
                    getSimilar(it.id)
                    getMovieTrailer(id)
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
        allSimilar = repo.getAllSimilar(id).asLiveData().cachedIn(viewModelScope)
        viewModelScope.launch {
            repo.getSimilar(id).collect {
                _similar.postValue(it)
            }
        }
    }

    private fun getMovieTrailer(id: Int) {
        viewModelScope.launch {
            repo.getMovieTrailer(id).collect {
                _playVideo.postValue(it)
            }
        }
    }
}