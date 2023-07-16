package com.sajjadio.trailers.ui.person_details

import android.graphics.Bitmap
import androidx.lifecycle.*
import com.sajjadio.trailers.domain.model.Person
import com.sajjadio.trailers.domain.repository.MovieRepository
import com.sajjadio.trailers.ui.person_details.adapter.PersonDetailsInteractListener
import com.sajjadio.trailers.ui.person_details.utils.PersonDetailsDestinationType
import com.sajjadio.trailers.ui.person_details.utils.PersonDetailsItem
import com.sajjadio.trailers.utils.Event
import com.sajjadio.trailers.domain.utils.Resource
import com.sajjadio.trailers.utils.language
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonDetailsViewModel @Inject constructor(
    private val movieRepo: MovieRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(), PersonDetailsInteractListener {

    private var _responsePersonDetailsData =
        MutableLiveData<Resource<List<PersonDetailsItem>>>()
    var responsePersonDetailsData: LiveData<Resource<List<PersonDetailsItem>>> =
        _responsePersonDetailsData
    private val personDetailsData = mutableListOf<PersonDetailsItem>()

    private val _clickItemEvent = MutableLiveData<Event<PersonDetailsDestinationType>>()
    val clickItemEvent: LiveData<Event<PersonDetailsDestinationType>> = _clickItemEvent

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val personId: Int = checkNotNull(savedStateHandle["personId"])

    var bitmap = MutableLiveData<Bitmap>()

    init {
        loadPersonData()
    }

    private fun loadPersonData() {
        personDetailsData.clear()
        personId.let {
            viewModelScope.launch {
                _isLoading.postValue(true)
                movieRepo.getPersonById(personId,language()).collect { state ->
                    when (state) {
                        is Resource.Success -> {
                            _isLoading.postValue(false)
                            state.data?.let { data ->
                                personDetailsData.add(PersonDetailsItem.PersonItem(data))
                                _responsePersonDetailsData.postValue(
                                    Resource.Success(
                                        personDetailsData
                                    )
                                )
                                getImageOfPersonById(personId)
                                getMoviesOfPerson(data.id)
                            }
                        }

                        is Resource.Error -> {
                            _isLoading.postValue(false)
                        }
                    }
                }
            }
        }
    }

    private fun getImageOfPersonById(personId: Int) {
        viewModelScope.launch {
            movieRepo.getImagesOfPersonById(personId).collect { state ->
                state.takeIf { it is Resource.Success }?.let {
                    it.data?.let { data ->
                        personDetailsData.add(PersonDetailsItem.GalleryOfPersonItem(data))
                        _responsePersonDetailsData.postValue(Resource.Success(personDetailsData))
                    }
                }
            }
        }
    }

    private fun getMoviesOfPerson(id: Int?) {
        viewModelScope.launch {
            movieRepo.getMoviesOfPersonById(id, language()).collect { state ->
                state.takeIf { it is Resource.Success }?.let {
                    it.data?.let { data ->
                        personDetailsData.add(PersonDetailsItem.MoviesOfPersonItem(data))
                        _responsePersonDetailsData.postValue(Resource.Success(personDetailsData))
                    }
                }
            }
        }
    }


    override fun onClickMovieItem(id: Int) {
        _clickItemEvent.postValue(Event(PersonDetailsDestinationType.MovieItem(id)))
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

    override fun onClickToShowBottomSheet(item: Person, listener: PersonDetailsInteractListener) {
        _clickItemEvent.postValue(Event(PersonDetailsDestinationType.BottomSheet(item, listener)))
    }
}