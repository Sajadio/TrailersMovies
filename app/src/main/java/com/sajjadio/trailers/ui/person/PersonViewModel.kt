package com.sajjadio.trailers.ui.person

import android.util.Log
import androidx.lifecycle.*
import com.sajjadio.trailers.domain.repository.MovieRepository
import com.sajjadio.trailers.ui.person.adapter.PersonDetailsInteractListener
import com.sajjadio.trailers.ui.person.utils.PersonDetailsDestinationType
import com.sajjadio.trailers.ui.person.utils.PersonDetailsItem
import com.sajjadio.trailers.utils.Event
import com.sajjadio.trailers.utils.NetworkStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(
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
                        personDetailsData.add(PersonDetailsItem.GalleryOFPersonItem(data))
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


    override fun onClickSeeAllGallery() {
        _clickItemEvent.postValue(Event(PersonDetailsDestinationType.Galleries))
    }

    override fun onClickMovieItem(id: Int) {
        _clickItemEvent.postValue(Event(PersonDetailsDestinationType.MovieItem(id)))
    }

    override fun onClickSeeAllMovies() {
        _clickItemEvent.postValue(Event(PersonDetailsDestinationType.Movies))
    }

    override fun onClickGalleryItem() {
        _clickItemEvent.postValue(Event(PersonDetailsDestinationType.GalleryItem))
    }

    override fun onClickItem(id: Int) {
        _clickItemEvent.postValue(Event(PersonDetailsDestinationType.MovieItem(id)))
    }
}