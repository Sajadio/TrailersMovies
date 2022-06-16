package com.example.trailers.ui.fragment.common.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.trailers.data.model.movie.common.CommonResult
import com.example.trailers.data.repository.common.CommonRepo
import com.example.trailers.ui.base.BaseViewModel
import com.example.trailers.utils.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class CommonViewModel @Inject constructor(
    private val commonRepo: CommonRepo,
) : BaseViewModel() {

    lateinit var responseCommonPagingData: LiveData<PagingData<CommonResult>>


    fun checkDestination(id: Int) {
        when (id) {
            Constant.POPULAR -> responseCommonPagingData =
                commonRepo.getPopularMoviePaging().cachedIn(viewModelScope)
                    .asLiveData()
            Constant.TOP_RATED -> responseCommonPagingData =
                commonRepo.getTopRatedMoviePaging().cachedIn(viewModelScope)
                    .asLiveData()
            Constant.UP_COMING -> responseCommonPagingData =
                commonRepo.getUpComingMoviePaging().cachedIn(viewModelScope)
                    .asLiveData()
        }
    }
}