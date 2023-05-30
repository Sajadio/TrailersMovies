package com.sajjadio.trailers.ui.home.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.*
import com.sajjadio.trailers.data.model.HomeItem
import com.sajjadio.trailers.data.repository.MovieRepository
import com.sajjadio.trailers.ui.home.adapter.HomeInteractListener
import com.sajjadio.trailers.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@SuppressLint("StaticFieldLeak")
class HomeViewModel @Inject constructor(
    private val movieRepo: MovieRepository,
) : ViewModel(), HomeInteractListener {

    private var _responseHomeData: MutableLiveData<NetworkStatus<List<HomeItem>>> =
        MutableLiveData()
    var responseHomeData: LiveData<NetworkStatus<List<HomeItem>>> = _responseHomeData
    private val homeData = mutableListOf<HomeItem>()

    init {
        refreshData()
    }

    fun refreshData() {
        homeData.clear()
        setUpTrendData()
        setUpPopularData()
        setUpRatedData()
        setUpUpComingData()
    }

    private fun setUpUpComingData() {
        viewModelScope.launch {
            movieRepo.getUpComingMovie().collect { state ->
                state.takeIf { it is NetworkStatus.Success }?.let {
                    it.data()?.let { data ->
                        homeData.add(HomeItem.Upcoming(data.results))
                        _responseHomeData.postValue(NetworkStatus.Success(homeData))
                    }
                }
            }
        }
    }


    private fun setUpRatedData() {
        viewModelScope.launch {
            movieRepo.getMovieTopRated().collect { state ->
                state.takeIf { it is NetworkStatus.Success }?.let {
                    it.data()?.let { data ->
                        homeData.add(HomeItem.TopRated(data.results))
                        _responseHomeData.postValue(NetworkStatus.Success(homeData))
                    }
                }
            }
        }
    }

    private fun setUpPopularData() {
        viewModelScope.launch {
            movieRepo.getMoviePopular().collect { state ->
                state.takeIf { it is NetworkStatus.Success }?.let {
                    it.data()?.let { data ->
                        homeData.add(HomeItem.Popular(data.results))
                        _responseHomeData.postValue(NetworkStatus.Success(homeData))
                    }
                }
            }
        }
    }

    private fun setUpTrendData() {
        viewModelScope.launch {
            movieRepo.getTrendMovie().collect { state ->
                state.takeIf { it is NetworkStatus.Success }?.let {
                    it.data()?.let { data ->
                        homeData.add(HomeItem.Trend(data.results))
                        _responseHomeData.postValue(NetworkStatus.Success(homeData))
                    }
                }
            }
        }
    }
}

