package com.sajjadio.trailers.ui.details

import androidx.lifecycle.*
import com.sajjadio.trailers.data.model.movie.id.IDMovie
import com.sajjadio.trailers.data.model.movie.video.VideoMovie
import com.sajjadio.trailers.domain.repository.MovieRepository
import com.sajjadio.trailers.ui.details.utils.DestinationType
import com.sajjadio.trailers.ui.details.utils.DetailsItem
import com.sajjadio.trailers.utils.Event
import com.sajjadio.trailers.utils.NetworkStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val movieRepo: MovieRepository,
) : ViewModel(), DetailsInteractListener {

    private var _responseData = MutableLiveData<NetworkStatus<IDMovie?>>()
    var responseData: LiveData<NetworkStatus<IDMovie?>> = _responseData

    private var _playVideo = MutableLiveData<NetworkStatus<VideoMovie?>>()
    val playVideo: LiveData<NetworkStatus<VideoMovie?>> = _playVideo

    private var _responseDetailsData = MutableLiveData<NetworkStatus<List<DetailsItem>>>()
    var responseDetailsData: LiveData<NetworkStatus<List<DetailsItem>>> = _responseDetailsData
    private val detailsData = mutableListOf<DetailsItem>()

    private val _clickItemEvent = MutableLiveData<Event<DestinationType>>()
    val clickItemEvent: LiveData<Event<DestinationType>> = _clickItemEvent

    fun getMovieId(id: Int?) {
        detailsData.clear()
        id?.let {
            viewModelScope.launch {
                movieRepo.getMoviesDetails(id).collect { state ->
                    state.takeIf { it is NetworkStatus.Success }?.let {
                        it.data()?.let { data ->
                            detailsData.add(DetailsItem.MovieItem(data))
                            _responseDetailsData.postValue(NetworkStatus.Success(detailsData))
                            getActorsByMovieId(data.id)
                            getSimilarByMovieId(data.id)
                            getTrailerOfMovie(id)
                        }
                    }
                }
            }
        }
    }

    private fun getActorsByMovieId(id: Int) {
        viewModelScope.launch {
            movieRepo.getActors(id).collect { state ->
                state.takeIf { it is NetworkStatus.Success }?.let {
                    it.data()?.let { data ->
                        detailsData.add(DetailsItem.ActorItem(data))
                        _responseDetailsData.postValue(NetworkStatus.Success(detailsData))
                    }
                }
            }
        }
    }

    private fun getSimilarByMovieId(id: Int) {
        viewModelScope.launch {
            movieRepo.getSimilar(id).collect { state ->
                state.takeIf { it is NetworkStatus.Success }?.let {
                    it.data()?.let { data ->
                        detailsData.add(DetailsItem.SimilarItem(data))
                        _responseDetailsData.postValue(NetworkStatus.Success(detailsData))
                    }
                }
            }
        }
    }

    private fun getTrailerOfMovie(id: Int) {
        viewModelScope.launch {
            movieRepo.getMovieTrailer(id).collect {
                _playVideo.postValue(it)
            }
        }
    }

    override fun onSeeAllActorsClick() {
        _clickItemEvent.postValue(Event(DestinationType.Actors))
    }

    override fun onActorItemClick(id: Int) {
        _clickItemEvent.postValue(Event(DestinationType.ActorItem(id)))
    }

    override fun onSeeAllSimilarClick() {
        _clickItemEvent.postValue(Event(DestinationType.Similar))
    }

    override fun onSimilarItemClick(id: Int) {
        _clickItemEvent.postValue(Event(DestinationType.SimilarItem(id)))
    }

}