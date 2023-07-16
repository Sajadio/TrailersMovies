package com.sajjadio.trailers.ui.home

import android.annotation.SuppressLint
import androidx.lifecycle.*
import com.sajjadio.trailers.ui.home.utils.HomeItem
import com.sajjadio.trailers.domain.repository.MovieRepository
import com.sajjadio.trailers.domain.utils.Resource
import com.sajjadio.trailers.ui.home.adapter.HomeInteractListener
import com.sajjadio.trailers.utils.Destination
import com.sajjadio.trailers.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
@SuppressLint("StaticFieldLeak")
class HomeViewModel @Inject constructor(
    private val movieRepo: MovieRepository,
) : ViewModel(), HomeInteractListener {

    private val _clickItemEvent = MutableLiveData<Event<Int>>()
    val clickItemEvent: LiveData<Event<Int>> = _clickItemEvent

    private val _clickShowAllItemEvent = MutableLiveData<Event<Destination>>()
    val clickShowAllItemEvent: LiveData<Event<Destination>> = _clickShowAllItemEvent

    private val mediatorLiveData = MediatorLiveData<List<HomeItem>>()
    val responseHomeData: LiveData<List<HomeItem>> = mediatorLiveData
    private lateinit var homeData: MutableList<HomeItem>

    private var _videoUrl = MutableLiveData<Event<String?>>()
    val videoUrl: LiveData<Event<String?>> = _videoUrl

    private val _loadingState = MutableLiveData(true)
    val loadingState: LiveData<Boolean> = _loadingState

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    init {
        refreshData()
    }

    fun refreshData() {
        viewModelScope.launch {
            _isRefreshing.postValue(true)
            movieRepo.refreshHomeItems(PAGE_NUMBER, language())
            loadData()
            _isRefreshing.postValue(false)
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            try {
                combine(
                    movieRepo.getUpComingMovie(language()),
                    movieRepo.getTopRatedMovies(language()),
                    movieRepo.getPopularMovies(language()),
                    movieRepo.getTrendMovies(language())
                ) { upComing, topRated, popular, trend ->
                    homeData = mutableListOf()
                    homeData.add(HomeItem.Upcoming(upComing))
                    homeData.add(HomeItem.TopRated(topRated))
                    homeData.add(HomeItem.Popular(popular))
                    homeData.add(HomeItem.Trend(trend))
                }.distinctUntilChanged()
                    .collect {
                        _loadingState.postValue(false)
                        mediatorLiveData.postValue(homeData)
                    }
            } catch (e: Throwable) {
                _loadingState.postValue(false)
            }
        }
    }

    private fun getTrailerOfMovie(id: Int) {
        viewModelScope.launch {
            movieRepo.getMovieTrailer(id).collect { status ->
                when (status) {
                    is Resource.Success -> {
                        status.data?.let {
                            _videoUrl.postValue(Event(it.first().key))
                        }
                    }
                    is Resource.Error -> {}
                }
            }
        }
    }

    override fun onClickItem(id: Int) {
        _clickItemEvent.postValue(Event(id))
    }

    override fun onClickSeeAllPopularItems(popular: Destination) {
        _clickShowAllItemEvent.postValue(Event(popular))
    }

    override fun onClickSeeAllTopRatedItems(topRated: Destination) {
        _clickShowAllItemEvent.postValue(Event(topRated))
    }

    override fun onClickSeeAllUpComingItems(upComing: Destination) {
        _clickShowAllItemEvent.postValue(Event(upComing))
    }

    override fun onClickWatchNow(id: Int) {
        getTrailerOfMovie(id)
    }

    private companion object {
        const val PAGE_NUMBER = 1
    }
}

