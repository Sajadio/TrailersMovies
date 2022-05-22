package com.example.trailers.ui.fragment.home.vm


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trailers.data.repository.HomeRepo
import com.example.trailers.utils.MultiViewTypeItem
import com.example.trailers.utils.ViewTypeHome
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val homeRepo: HomeRepo) : ViewModel() {

    private val _responseData: MutableLiveData<List<MultiViewTypeItem<Any>>> =
        MutableLiveData()
    val responseData: LiveData<List<MultiViewTypeItem<Any>>> = _responseData

    private val multiViewType = mutableListOf<MultiViewTypeItem<Any>>()

    init {
        _responseData.postValue(multiViewType)
        getTrend()
        getPopular()
        getTopRated()
        getUpComing()
    }


    private fun getTrend() {
        viewModelScope.launch {
            homeRepo.getTrending().collect { state ->
                state.data()?.let {
                    multiViewType.add(MultiViewTypeItem(it, ViewTypeHome.TREND.ordinal))
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

