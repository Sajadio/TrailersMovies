package com.sajjadio.trailers.ui.home.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.*
import com.sajjadio.trailers.data.model.HomeItem
import com.sajjadio.trailers.data.model.movie.common.Common
import com.sajjadio.trailers.data.model.movie.common.CommonResult
import com.sajjadio.trailers.data.model.movie.trend.TrendResult
import com.sajjadio.trailers.data.repository.home.HomeRepoImpl
import com.sajjadio.trailers.ui.home.adapter.HomeInteractListener
import com.sajjadio.trailers.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@SuppressLint("StaticFieldLeak")
class HomeViewModel @Inject constructor(
    private val homeRepo: HomeRepoImpl,
) : ViewModel(), HomeInteractListener {

    private var _responseTrendData: MutableLiveData<NetworkStatus<List<TrendResult>?>> =
        MutableLiveData()
    var responseTrendData: LiveData<NetworkStatus<List<TrendResult>?>> = _responseTrendData

    private var _responsePopularData: MutableLiveData<NetworkStatus<List<CommonResult>?>> =
        MutableLiveData()
    var responsePopularData: LiveData<NetworkStatus<List<CommonResult>?>> = _responsePopularData

    private var _responseRatedData: MutableLiveData<NetworkStatus<List<CommonResult>?>> =
        MutableLiveData()
    var responseRatedData: LiveData<NetworkStatus<List<CommonResult>?>> = _responseRatedData

    private var _responseUpComingData: MutableLiveData<NetworkStatus<List<CommonResult>?>> =
        MutableLiveData()
    var responseUpComingData: LiveData<NetworkStatus<List<CommonResult>?>> = _responseUpComingData

    private var _responseHomeData: MutableLiveData<NetworkStatus<List<HomeItem>>> =
        MutableLiveData()
    var responseHomeData: LiveData<NetworkStatus<List<HomeItem>>> = _responseHomeData
    private val homeData = mutableListOf<HomeItem>()

    val saveCurrentPosition = MutableLiveData<Int>()


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
            homeRepo.getUpComingMovie().collect { state ->
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
            homeRepo.getMovieTopRated().collect { state ->
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
            homeRepo.getMoviePopular().collect { state ->
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
            homeRepo.getTrendMovie().collect { state ->
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

