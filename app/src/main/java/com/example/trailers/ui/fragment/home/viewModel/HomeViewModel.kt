package com.example.trailers.ui.fragment.home.viewModel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.trailers.data.model.movie.common.Common
import com.example.trailers.data.model.movie.trend.TrendMovie
import com.example.trailers.data.repository.home.HomeRepoImpl
import com.example.trailers.utils.*
import com.example.trailers.utils.Constant.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

@HiltViewModel
@SuppressLint("StaticFieldLeak")
class HomeViewModel @Inject constructor(
    private val homeRepo: HomeRepoImpl,
    @ApplicationContext private val context: Context,
) : ViewModel() {

    private var _responseTrendData: MutableLiveData<NetworkStatus<TrendMovie>> = MutableLiveData()
    var responseTrendData: LiveData<NetworkStatus<TrendMovie>> = _responseTrendData

    private var _responsePopularData: MutableLiveData<NetworkStatus<Common>?> = MutableLiveData()
    var responsePopularData: LiveData<NetworkStatus<Common>?> = _responsePopularData

    private var _responseRatedData: MutableLiveData<NetworkStatus<Common>> = MutableLiveData()
    var responseRatedData: LiveData<NetworkStatus<Common>> = _responseRatedData

    private var _responseUpComingData: MutableLiveData<NetworkStatus<Common>> = MutableLiveData()
    var responseUpComingData: LiveData<NetworkStatus<Common>> = _responseUpComingData

    val saveCurrentPosition = MutableLiveData<Int>()


    init {
        refreshData()
    }

    fun refreshData() {
        if (isNetworkAvailable(context)) {
            setUpTrendData()
            setUpPopularData()
            setUpRatedData()
            setUpUpComingData()
        }
    }

    private fun setUpUpComingData() {
        viewModelScope.launch {
            homeRepo.getUpComingMovie().collect { state ->
                _responseUpComingData.postValue(state)
            }
        }
    }

    private fun setUpRatedData() {
        viewModelScope.launch {
            homeRepo.getMovieTopRated().collect { state ->
                _responseRatedData.postValue(state)
            }
        }
    }

    private fun setUpPopularData() {
        viewModelScope.launch {
            homeRepo.getMoviePopular().collect { state ->
                _responsePopularData.postValue(state)
            }
        }
    }

    private fun setUpTrendData() {
        viewModelScope.launch {
            homeRepo.getTrendMovie().collect { state ->
                _responseTrendData.postValue(state)
            }
        }
    }
}

