package com.sajjadio.trailers.ui.home

import android.annotation.SuppressLint
import androidx.lifecycle.*
import com.sajjadio.trailers.ui.home.utils.HomeItem
import com.sajjadio.trailers.domain.repository.MovieRepository
import com.sajjadio.trailers.ui.home.adapter.HomeInteractListener
import com.sajjadio.trailers.utils.Destination
import com.sajjadio.trailers.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
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

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> = _loadingState

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    init {
        refreshData()
        loadData()
    }

    fun refreshData() {
        viewModelScope.launch {
            _isRefreshing.postValue(true)
            movieRepo.refreshHomeItems(PAGE_NUMBER)
            _isRefreshing.postValue(false)
        }
    }

    private fun loadData() {
        _loadingState.postValue(true)
        viewModelScope.launch {
            try {
                combine(
                    movieRepo.getUpComingMovie(),
                    movieRepo.getTopRatedMovies(),
                    movieRepo.getPopularMovies(),
                    movieRepo.getTrendMovies()
                ) { upComing, topRated, popular, trend ->
                    val homeData = mutableListOf<HomeItem>()
                    homeData.add(HomeItem.Upcoming(upComing))
                    homeData.add(HomeItem.TopRated(topRated))
                    homeData.add(HomeItem.Popular(popular))
                    homeData.add(HomeItem.Trend(trend))
                    mediatorLiveData.postValue(homeData)
                }.collect {
                    _loadingState.postValue(false)
                }
            } catch (e: Throwable) {
                _loadingState.postValue(false)
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

    private companion object {
        const val PAGE_NUMBER = 1
    }
}

