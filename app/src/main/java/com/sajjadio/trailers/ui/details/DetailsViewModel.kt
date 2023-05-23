package com.sajjadio.trailers.ui.details

import androidx.lifecycle.*
import com.sajjadio.trailers.data.model.movie.actors.Actors
import com.sajjadio.trailers.data.model.movie.actorsmovie.ActorsMovie
import com.sajjadio.trailers.data.model.movie.actorsmovie.Cast
import com.sajjadio.trailers.data.model.movie.id.IDMovie
import com.sajjadio.trailers.data.model.movie.similar.Similar
import com.sajjadio.trailers.data.repository.movie.MovieRepoImpl
import com.sajjadio.trailers.data.model.movie.video.VideoMovie
import com.sajjadio.trailers.ui.actors.ActorInteractListener
import com.sajjadio.trailers.utils.NetworkStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repo: MovieRepoImpl
) : ViewModel(), ActorInteractListener,SimilarInteractionist {

    private var _responseData: MutableLiveData<NetworkStatus<IDMovie?>> = MutableLiveData()
    var responseData: LiveData<NetworkStatus<IDMovie?>> = _responseData

    private val _actors: MutableLiveData<NetworkStatus<Actors?>> = MutableLiveData()
    val actors: LiveData<NetworkStatus<Actors?>> = _actors

    private val _actorsOfMovie: MutableLiveData<NetworkStatus<List<Cast>?>> = MutableLiveData()
    val actorsOfMovie: LiveData<NetworkStatus<List<Cast>?>> = _actorsOfMovie

    private var _similar: MutableLiveData<NetworkStatus<Similar?>> = MutableLiveData()
    val similar: LiveData<NetworkStatus<Similar?>> = _similar

    private var _playVideo: MutableLiveData<NetworkStatus<VideoMovie?>> = MutableLiveData()
    val playVideo: LiveData<NetworkStatus<VideoMovie?>> = _playVideo

    fun getID(id: Int?) {

            id?.let {
                viewModelScope.launch {
                    repo.getMoviesDetails(id).collect { state ->
                        _responseData.postValue(state)

                        state.data()?.let {
                            getActors(it.id)
                            getSimilar(it.id)
                            getMovieTrailer(id)
                        }
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

    fun getMovieOfActor(person_id: Int?) {
        viewModelScope.launch {
            repo.getMovieOfActor(person_id).collect {
                _actorsOfMovie.postValue(NetworkStatus.Success(it.data()?.cast))
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

    private fun getMovieTrailer(id: Int) {
        viewModelScope.launch {
            repo.getMovieTrailer(id).collect {
                _playVideo.postValue(it)
            }
        }
    }

    override fun onActorItemClick(id: Int) {
        TODO("Not yet implemented")
    }

    override fun onSimilarItemClick(id: Int) {
        TODO("Not yet implemented")
    }
}