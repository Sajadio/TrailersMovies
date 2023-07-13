package com.sajjadio.trailers.ui.home.viewModel

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

    private var _responseHomeData = MutableLiveData<List<HomeItem>>()




    private val homeData = mutableListOf<HomeItem>()

    private val mediatorLiveData = MediatorLiveData<List<HomeItem>>()

    val responseHomeData: LiveData<List<HomeItem>> = mediatorLiveData

    init {
        refreshData()
    }

    fun refreshData() {
        viewModelScope.launch {
            movieRepo.refreshHomeItems()
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
                }.collect{}
            }
        }
    }

    private fun setUpUpComingData() {
        viewModelScope.launch {
            movieRepo.getUpComingMovie().collect { data ->
                homeData.add(HomeItem.Upcoming(data))
                _responseHomeData.postValue(homeData)
            }
        }
    }

    private fun setUpRatedData() {
        viewModelScope.launch {
            movieRepo.getTopRatedMovies().collect { data ->
                homeData.add(HomeItem.TopRated(data))
                _responseHomeData.postValue(homeData)
            }
        }
    }

    private fun setUpPopularData() {
        viewModelScope.launch {
            movieRepo.getPopularMovies().collect { data ->
                homeData.add(HomeItem.Popular(data))
                _responseHomeData.postValue(homeData)
            }
        }
    }

    private fun setUpTrendData() {
        viewModelScope.launch {
            movieRepo.getTrendMovies().collect { data ->
                homeData.add(HomeItem.Trend(data))
                _responseHomeData.postValue(homeData)
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
}

