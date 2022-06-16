package com.example.trailers.ui.fragment.home.vm

import androidx.lifecycle.*
import com.example.trailers.data.repository.home.HomeRepo
import com.example.trailers.ui.base.BaseViewModel
import com.example.trailers.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeViewModel @Inject constructor(
    private val homeRepo: HomeRepo,
) : BaseViewModel() {

    private val _responseData: MutableLiveData<List<MultiViewTypeItem<NetworkStatus<Any>>>> =
        MutableLiveData()
    val responseData: LiveData<List<MultiViewTypeItem<NetworkStatus<Any>>>> = _responseData
    private val multiViewType = mutableSetOf<MultiViewTypeItem<NetworkStatus<Any>>>()


    fun checkConnection(isConnection: Boolean?) {
        viewModelScope.launch {
            if (isConnection == true) {

                val trend = async(Dispatchers.IO) { getTrend() }
                val popular = async(Dispatchers.IO) { getPopular() }
                val rated = async(Dispatchers.IO) { getTopRated() }
                val coming = async(Dispatchers.IO) { getUpComing() }

                if (trend.await() == popular.await() && rated.await() == coming.await())
                        _responseData.postValue(multiViewType.toList().sortedBy { it.viewType })
                else
                    _responseData.postValue(emptyList())

            }
        }
    }

    private suspend fun getTrend() {
        homeRepo.getTrendMovie().collect { state ->
            multiViewType.add(MultiViewTypeItem(state, ViewType.TRENDING.ordinal))
        }
    }

    private suspend fun getPopular() {
        homeRepo.getMoviePopular().collect { state ->
            multiViewType.add(MultiViewTypeItem(null, ViewType.HEADER_VIEW_POPULAR.ordinal))
            multiViewType.add(MultiViewTypeItem(state, ViewType.POPULAR.ordinal))
        }
    }

    private suspend fun getTopRated() {
        homeRepo.getMovieTopRated().collect { state ->
            multiViewType.add(MultiViewTypeItem(null, ViewType.HEADER_VIEW_TOP_RATED.ordinal))
            multiViewType.add(MultiViewTypeItem(state, ViewType.RATED.ordinal))
        }
    }

    private suspend fun getUpComing() {
        homeRepo.getUpComingMovie().collect { state ->
            multiViewType.add(MultiViewTypeItem(null, ViewType.HEADER_VIEW_UPCOMING.ordinal))
            multiViewType.add(MultiViewTypeItem(state, ViewType.UPCOMING.ordinal))
        }
    }

}

