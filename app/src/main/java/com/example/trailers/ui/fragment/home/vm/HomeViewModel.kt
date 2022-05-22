package com.example.trailers.ui.fragment.home.vm


import androidx.lifecycle.*
import com.example.trailers.data.loacal.playnow.PlayNowResultEntity
import com.example.trailers.data.repository.HomeRepo
import com.example.trailers.utils.MultiViewTypeItem
import com.example.trailers.utils.NetworkStatus
import com.example.trailers.utils.ViewTypeHome
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeViewModel @Inject constructor(private val homeRepo: HomeRepo) : ViewModel() {

    private val _responseData: MutableLiveData<List<MultiViewTypeItem<Any>>> =
        MutableLiveData()
    val responseData: LiveData<List<MultiViewTypeItem<Any>>> = _responseData

    val requestStatus: LiveData<NetworkStatus<Any?>> =
        homeRepo.getMoviePlayNow().asLiveData()

    val connection: MutableLiveData<Int> = MutableLiveData(0)
    val checkConnection: LiveData<Int> = connection

    private val multiViewType = mutableListOf<MultiViewTypeItem<Any>>()

    init {
        _responseData.postValue(multiViewType)
        getPlayNow()
        getPopular()
        getTopRated()
        getUpComing()
    }


    private fun getPlayNow() {
        viewModelScope.launch {
            homeRepo.getMoviePlayNow().collect { state ->
                state.data()?.let {
                    multiViewType.add(MultiViewTypeItem(it, ViewTypeHome.PLAY_NOW.ordinal))
                    multiViewType.add(MultiViewTypeItem("",
                        ViewTypeHome.HEADER_VIEW_POPULAR.ordinal))
                }
            }
        }
    }


    private fun getPopular() {
        viewModelScope.launch {
            homeRepo.getMoviePopular().collect { state ->
                state.data()?.let {

                    multiViewType.add(MultiViewTypeItem(it, ViewTypeHome.POPULAR.ordinal))
                }
            }
        }
    }


    private fun getTopRated() {
        viewModelScope.launch {
            homeRepo.getMovieTopRated().collect { state ->
                state.data()?.let {
                    multiViewType.add(MultiViewTypeItem("",
                        ViewTypeHome.HEADER_VIEW_TOP_RATED.ordinal))
                    multiViewType.add(MultiViewTypeItem(it, ViewTypeHome.RATED.ordinal))
                }
            }
        }
    }

    private fun getUpComing() {
        viewModelScope.launch {
            homeRepo.getUpComingMovie().collect { state ->
                state.data()?.let {
                    multiViewType.add(MultiViewTypeItem("",
                        ViewTypeHome.HEADER_VIEW_UPCOMING.ordinal))
                    multiViewType.add(MultiViewTypeItem(it, ViewTypeHome.UPCOMING.ordinal))
                }
            }
        }
    }


}

