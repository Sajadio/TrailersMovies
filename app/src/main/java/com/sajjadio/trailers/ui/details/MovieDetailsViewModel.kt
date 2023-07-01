package com.sajjadio.trailers.ui.details

import android.graphics.Bitmap
import androidx.lifecycle.*
import com.sajjadio.trailers.data.model.movie.video.VideoMovie
import com.sajjadio.trailers.domain.repository.MovieRepository
import com.sajjadio.trailers.ui.details.adapter.MovieDetailsInteractListener
import com.sajjadio.trailers.ui.details.utils.MovieDetailsDestinationType
import com.sajjadio.trailers.ui.details.utils.MovieDetailsItem
import com.sajjadio.trailers.utils.Event
import com.sajjadio.trailers.utils.NetworkStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieRepo: MovieRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(), MovieDetailsInteractListener {

    private var _playVideo = MutableLiveData<NetworkStatus<VideoMovie?>>()
    val playVideo: LiveData<NetworkStatus<VideoMovie?>> = _playVideo

    private var _responseDetailsData = MutableLiveData<NetworkStatus<List<MovieDetailsItem>>>()
    var responseDetailsData: LiveData<NetworkStatus<List<MovieDetailsItem>>> = _responseDetailsData
    private val detailsData = mutableListOf<MovieDetailsItem>()

    private val _clickItemEvent = MutableLiveData<Event<MovieDetailsDestinationType>>()
    val clickItemEvent: LiveData<Event<MovieDetailsDestinationType>> = _clickItemEvent

    private val movieId: Int = checkNotNull(savedStateHandle["movieId"])

    var bitmap = MutableLiveData<Bitmap>()



    init {
        loadMovieData()
    }

    private fun loadMovieData() {
        detailsData.clear()
        movieId.let {
            viewModelScope.launch {
                movieRepo.getMovieDetails(movieId).collect { state ->
                    state.takeIf { it is NetworkStatus.Success }?.let {
                        it.data?.let { data ->
                            detailsData.add(MovieDetailsItem.MovieItem(data))
                            _responseDetailsData.postValue(NetworkStatus.Success(detailsData))
                            getActorsByMovieId(data.id)
                            getImageOfMovieById(movieId)
                            getSimilarByMovieId(data.id)
                            getTrailerOfMovie(movieId)
                        }
                    }
                }
            }
        }
    }

    private fun getImageOfMovieById(movieId: Int) {
        viewModelScope.launch {
            movieRepo.getImagesOfMovieById(movieId).collect { state ->
                state.takeIf { it is NetworkStatus.Success }?.let {
                    it.data?.let { data ->
                        detailsData.add(MovieDetailsItem.GalleryItem(data))
                        _responseDetailsData.postValue(NetworkStatus.Success(detailsData))
                    }
                }
            }
        }
    }

    private fun getActorsByMovieId(id: Int) {
        viewModelScope.launch {
            movieRepo.getPersonOfMovieById(id).collect { state ->
                state.takeIf { it is NetworkStatus.Success }?.let {
                    it.data?.let { data ->
                        detailsData.add(MovieDetailsItem.PersonOfMovieItem(data))
                        _responseDetailsData.postValue(NetworkStatus.Success(detailsData))
                    }
                }
            }
        }
    }

    private fun getSimilarByMovieId(id: Int) {
        viewModelScope.launch {
            movieRepo.getSimilar(id, PAGE_NUMBER).collect { state ->
                state.takeIf { it is NetworkStatus.Success }?.let {
                    it.data?.let { data ->
                        detailsData.add(MovieDetailsItem.SimilarItemMovie(data))
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

    override fun onClickSeeAllGallery() {
        _clickItemEvent.postValue(Event(MovieDetailsDestinationType.Galleries))
    }

    override fun onClickGalleryItem() {
        _clickItemEvent.postValue(Event(MovieDetailsDestinationType.GalleryItem))
    }

    override fun onClickSeeAllPersons() {
        _clickItemEvent.postValue(Event(MovieDetailsDestinationType.Persons))
    }

    override fun onClickPersonItem(id: Int) {
        _clickItemEvent.postValue(Event(MovieDetailsDestinationType.PersonItem(id)))
    }


    override fun onClickSeeAllSimilar() {
        _clickItemEvent.postValue(Event(MovieDetailsDestinationType.Similar))
    }

    override fun onClickDownloadImage(bitmap: Bitmap) {
        this.bitmap.postValue(bitmap)
    }

    override fun onClickItem(id: Int) {
        _clickItemEvent.postValue(Event(MovieDetailsDestinationType.SimilarItem(id)))
    }

    private companion object {
        const val PAGE_NUMBER = 1
    }
}