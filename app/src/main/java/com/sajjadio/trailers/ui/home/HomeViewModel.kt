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

    init {
        refreshData()
    }

    fun refreshData() {
        viewModelScope.launch {
            movieRepo.refreshHomeItems(PAGE_NUMBER)
            viewModelScope.launch {
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
                }.collect {}
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

