package com.sajjadio.trailers.ui.person_details

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.*
import com.sajjadio.trailers.domain.repository.MovieRepository
import com.sajjadio.trailers.ui.person_details.adapter.PersonDetailsInteractListener
import com.sajjadio.trailers.ui.person_details.utils.PersonDetailsDestinationType
import com.sajjadio.trailers.ui.person_details.utils.PersonDetailsItem
import com.sajjadio.trailers.utils.Event
import com.sajjadio.trailers.utils.NetworkStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonDetailsViewModel @Inject constructor(
    private val movieRepo: MovieRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(), PersonDetailsInteractListener {

    private var _responsePersonDetailsData =
        MutableLiveData<NetworkStatus<List<PersonDetailsItem>>>()
    var responsePersonDetailsData: LiveData<NetworkStatus<List<PersonDetailsItem>>> =
        _responsePersonDetailsData
    private val personDetailsData = mutableListOf<PersonDetailsItem>()

    private val _clickItemEvent = MutableLiveData<Event<PersonDetailsDestinationType>>()
    val clickItemEvent: LiveData<Event<PersonDetailsDestinationType>> = _clickItemEvent

    private val personId: Int = checkNotNull(savedStateHandle["personId"])

    var bitmap = MutableLiveData<Bitmap>()

    init {
        loadPersonData()
    }

    private fun loadPersonData() {
        personDetailsData.clear()
        personId.let {
            viewModelScope.launch {
                movieRepo.getPersonById(personId).collect { state ->
                    state.takeIf { it is NetworkStatus.Success }?.let {
                        it.data?.let { data ->
                            personDetailsData.add(PersonDetailsItem.PersonItem(data))
                            _responsePersonDetailsData.postValue(
                                NetworkStatus.Success(
                                    personDetailsData
                                )
                            )
                            getImageOfPersonById(personId)
                            getMoviesOfPerson(data.id)
                        }
                    }
                }
            }
        }
    }

    private fun getImageOfPersonById(personId: Int) {
        viewModelScope.launch {
            movieRepo.getImagesOfPersonById(personId).collect { state ->
                state.takeIf { it is NetworkStatus.Success }?.let {
                    it.data?.let { data ->
                        personDetailsData.add(PersonDetailsItem.GalleryOfPersonItem(data))
                        _responsePersonDetailsData.postValue(NetworkStatus.Success(personDetailsData))
                    }
                }
            }
        }
    }

    private fun getMoviesOfPerson(id: Int?) {
        viewModelScope.launch {
            movieRepo.getMoviesOfPersonById(id).collect { state ->
                state.takeIf { it is NetworkStatus.Success }?.let {
                    it.data?.let { data ->
                        personDetailsData.add(PersonDetailsItem.MoviesOfPersonItem(data))
                        _responsePersonDetailsData.postValue(NetworkStatus.Success(personDetailsData))
                    }
                }
            }
        }
    }


    override fun onClickMovieItem(id: Int) {
        _clickItemEvent.postValue(Event(PersonDetailsDestinationType.MovieItem(id)))
    }

    override fun onClickSeeAllMovies() {
        _clickItemEvent.postValue(Event(PersonDetailsDestinationType.Movies))
    }

    override fun onClickDownloadImage(bitmap: Bitmap) {
        this.bitmap.postValue(bitmap)
    }

    override fun onClickBackButton() {
        _clickItemEvent.postValue(Event(PersonDetailsDestinationType.BackButton))
    }

    override fun onClickItem(id: Int) {
        _clickItemEvent.postValue(Event(PersonDetailsDestinationType.MovieItem(id)))
    }

    private companion object {
        const val PAGE_NUMBER = 1
    }
}