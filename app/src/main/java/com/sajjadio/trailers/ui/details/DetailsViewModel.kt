package com.sajjadio.trailers.ui.details

import android.util.Log
import androidx.lifecycle.*
import com.sajjadio.trailers.data.model.HomeItem
import com.sajjadio.trailers.data.model.movie.actors.Actors
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
) : ViewModel(), DetailsInteractListener {

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


    private var _responseDetailsData: MutableLiveData<NetworkStatus<List<DetailsItem>>> =
        MutableLiveData()
    var responseDetailsData: LiveData<NetworkStatus<List<DetailsItem>>> = _responseDetailsData
    private val detailsData = mutableListOf<DetailsItem>()

    fun getID(id: Int?) {
        id?.let {
            viewModelScope.launch {
                repo.getMoviesDetails(id).collect { state ->
                    state.takeIf { it is NetworkStatus.Success }?.let {
                        it.data()?.let { data ->
                            detailsData.add(DetailsItem.MovieItem(data))
                            _responseDetailsData.postValue(NetworkStatus.Success(detailsData))
                        }
                    }
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
            repo.getActors(id).collect { state ->
                state.takeIf { it is NetworkStatus.Success }?.let {
                    it.data()?.let { data ->
                        detailsData.add(DetailsItem.ActorItem(data))
                        _responseDetailsData.postValue(NetworkStatus.Success(detailsData))
                    }
                }
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
            repo.getSimilar(id).collect { state ->
                state.takeIf { it is NetworkStatus.Success }?.let {
                    it.data()?.let { data ->
                        detailsData.add(DetailsItem.SimilarItem(data))
                        _responseDetailsData.postValue(NetworkStatus.Success(detailsData))
                    }
                }
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

    override fun onSeeAllActorsClick() {
        Log.d("sajjadio", "onSeeAllActorsClick: you clicked me")
    }

    override fun onActorItemClick(id: Int) {
        Log.d("sajjadio", "onActorItemClick: you clicked me $id")
    }

    override fun onSeeAllSimilarClick() {
        Log.d("sajjadio", "onSeeAllSimilarClick: clicked me")
    }

}