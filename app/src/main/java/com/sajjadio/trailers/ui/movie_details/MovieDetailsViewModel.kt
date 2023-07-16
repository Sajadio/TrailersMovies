package com.sajjadio.trailers.ui.movie_details

import android.graphics.Bitmap
import androidx.lifecycle.*
import com.sajjadio.trailers.domain.model.MovieDetails
import com.sajjadio.trailers.domain.repository.MovieRepository
import com.sajjadio.trailers.ui.movie_details.adapter.MovieDetailsInteractListener
import com.sajjadio.trailers.ui.movie_details.utils.MovieDetailsDestinationType
import com.sajjadio.trailers.ui.movie_details.utils.MovieDetailsItem
import com.sajjadio.trailers.utils.Event
import com.sajjadio.trailers.domain.utils.Resource
import com.sajjadio.trailers.utils.language
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieRepo: MovieRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(), MovieDetailsInteractListener {

    private var _responseDetailsData = MutableLiveData<Resource<List<MovieDetailsItem>>>()
    var responseDetailsData: LiveData<Resource<List<MovieDetailsItem>>> = _responseDetailsData
    private val detailsData = mutableListOf<MovieDetailsItem>()

    private val _clickItemEvent = MutableLiveData<Event<MovieDetailsDestinationType>>()
    val clickItemEvent: LiveData<Event<MovieDetailsDestinationType>> = _clickItemEvent

    private val movieId: Int = checkNotNull(savedStateHandle["movieId"])

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private var _videoUrl = MutableLiveData<String?>()
    val videoUrl: LiveData<String?> = _videoUrl


    var bitmap = MutableLiveData<Bitmap>()

    init {
        checkIsMovieSaved(movieId)
    }

    private fun checkIsMovieSaved(movieId: Int) {
        viewModelScope.launch {
            movieRepo.checkIsMovieSaved(movieId).collect {
                loadMovieData(movieId,it)
            }
        }
    }

    private fun loadMovieData(movieId: Int, isItemSaved: Boolean) {
        this.movieId.let {
            viewModelScope.launch {
                when (val resource = movieRepo.getMovieById(movieId, language(), isItemSaved)) {
                    is Resource.Success -> {
                        _isLoading.postValue(false)
                        resource.data?.let {
                            detailsData.clear()
                            detailsData.add(MovieDetailsItem.MovieItem(it))
                            getPersonsByMovieId(it.id)
                            getImageOfMovieById(this@MovieDetailsViewModel.movieId)
                            getSimilarByMovieId(it.id)
                            _responseDetailsData.postValue(Resource.Success(detailsData))
                        }
                    }

                    is Resource.Error -> {
                        _isLoading.postValue(false)
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
            movieRepo.getPersonsOfMovieById(id, language()).collect { state ->
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
            movieRepo.getSimilarOfMovieById(id, PAGE_NUMBER, language()).collect { state ->
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
            movieRepo.getMovieTrailer(id).collect { status ->
                when (status) {
                    is Resource.Success -> {
                        status.data?.let {
                            _videoUrl.postValue(it.first().key)
                        }
                    }

                    is Resource.Error -> {
                    }
                }
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

    override fun onClickDeleteFavoriteItemButton(movieId: Int) {
        viewModelScope.launch {
            movieRepo.deleteMovie(movieId)
        }
    }

    override fun onClickToShowBottomSheet(
        item: MovieDetails,
        listener: MovieDetailsInteractListener
    ) {
        _clickItemEvent.postValue(Event(MovieDetailsDestinationType.BottomSheet(item, listener)))
    }

    override fun onClickWatchNow(id: Int) {
        getTrailerOfMovie(id)
        _clickItemEvent.postValue(Event(MovieDetailsDestinationType.WatchNowMovie))
    }

    override fun onClickItem(id: Int) {
        _clickItemEvent.postValue(Event(MovieDetailsDestinationType.SimilarItem(id)))
    }

    private companion object {
        const val PAGE_NUMBER = 1
    }
}