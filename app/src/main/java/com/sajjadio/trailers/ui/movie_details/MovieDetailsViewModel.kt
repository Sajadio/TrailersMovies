package com.sajjadio.trailers.ui.movie_details

import android.graphics.Bitmap
import androidx.lifecycle.*
import com.sajjadio.trailers.data.dataSource.model.movie.video.VideoMovie
import com.sajjadio.trailers.domain.model.MovieDetails
import com.sajjadio.trailers.domain.repository.MovieRepository
import com.sajjadio.trailers.ui.movie_details.adapter.MovieDetailsInteractListener
import com.sajjadio.trailers.ui.movie_details.utils.MovieDetailsDestinationType
import com.sajjadio.trailers.ui.movie_details.utils.MovieDetailsItem
import com.sajjadio.trailers.utils.Event
import com.sajjadio.trailers.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieRepo: MovieRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(), MovieDetailsInteractListener {

    private var _playVideo = MutableLiveData<Resource<VideoMovie?>>()
    val playVideo: LiveData<Resource<VideoMovie?>> = _playVideo

    private var _responseDetailsData = MutableLiveData<Resource<List<MovieDetailsItem>>>()
    var responseDetailsData: LiveData<Resource<List<MovieDetailsItem>>> = _responseDetailsData
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
                when(val resource =  movieRepo.getMovieDetails(movieId)){
                    is Resource.Success -> {
                        resource.data?.let {
                            detailsData.add(MovieDetailsItem.MovieItem(it))
                            _responseDetailsData.postValue(Resource.Success(detailsData))
                            getPersonsByMovieId(it.id)
                            getImageOfMovieById(movieId)
                            getSimilarByMovieId(it.id)
                            getTrailerOfMovie(movieId)
                        }
                    }

                    is Resource.Error -> {
                        _responseDetailsData.postValue(Resource.Error(resource.errorMessage))
                    }
                }
            }
        }
    }

    private fun getImageOfMovieById(movieId: Int) {
        viewModelScope.launch {
            movieRepo.getImagesOfMovieById(movieId).collect { state ->
                state.takeIf { it is Resource.Success }?.let {
                    it.data?.let { data ->
                        detailsData.add(MovieDetailsItem.GalleryItem(data))
                        _responseDetailsData.postValue(Resource.Success(detailsData))
                    }
                }
            }
        }
    }

    private fun getPersonsByMovieId(id: Int) {
        viewModelScope.launch {
            movieRepo.getPersonOfMovieById(id).collect { state ->
                state.takeIf { it is Resource.Success }?.let {
                    it.data?.let { data ->
                        detailsData.add(MovieDetailsItem.PersonOfMovieItem(data))
                        _responseDetailsData.postValue(Resource.Success(detailsData))
                    }
                }
            }
        }
    }

    private fun getSimilarByMovieId(id: Int) {
        viewModelScope.launch {
            movieRepo.getSimilar(id, PAGE_NUMBER).collect { state ->
                state.takeIf { it is Resource.Success }?.let {
                    it.data?.let { data ->
                        detailsData.add(MovieDetailsItem.SimilarItemMovie(data))
                        _responseDetailsData.postValue(Resource.Success(detailsData))
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

    override fun onClickBackButton() {
        _clickItemEvent.postValue(Event(MovieDetailsDestinationType.BackButton))
    }

    override fun onClickFavoriteButton(movieDetails: MovieDetails) {
        viewModelScope.launch {
            movieRepo.addMovie(movieDetails)
        }
    }

    override fun onClickToShowBottomSheet(item: MovieDetails, listener: MovieDetailsInteractListener) {
        _clickItemEvent.postValue(Event(MovieDetailsDestinationType.BottomSheet(item,listener)))
    }

    override fun onClickWatchNow(id: Int) {
        _clickItemEvent.postValue(Event(MovieDetailsDestinationType.WatchNowMovie(id)))
    }

    override fun onClickItem(id:Int) {
        _clickItemEvent.postValue(Event(MovieDetailsDestinationType.SimilarItem(id)))
    }

    private companion object {
        const val PAGE_NUMBER = 1
    }
}