package com.sajjadio.trailers.ui.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sajjadio.trailers.data.dataSource.model.movie.common.CommonResultDto
import com.sajjadio.trailers.domain.model.CommonResult
import com.sajjadio.trailers.domain.repository.MovieRepository
import com.sajjadio.trailers.ui.base.BaseInteractListener
import com.sajjadio.trailers.utils.Destination
import com.sajjadio.trailers.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class CommonViewModel @Inject constructor(
    private val movieRepo: MovieRepository,
) : ViewModel(),BaseInteractListener {

    lateinit var responseCommonPagingData: LiveData<PagingData<CommonResult>>
    var title: String? = null
    private val _clickItemEvent = MutableLiveData<Event<Int>>()
    val clickItemEvent: LiveData<Event<Int>> = _clickItemEvent

    fun checkDestination(destination: Destination) {
        when (destination) {
            Destination.Popular -> {
                responseCommonPagingData = movieRepo
                    .getPopularMoviePaging()
                    .cachedIn(viewModelScope + Dispatchers.Main)
                    .asLiveData()
            }

            Destination.TopRated -> {
                responseCommonPagingData = movieRepo
                    .getTopRatedMoviePaging()
                    .cachedIn(viewModelScope + Dispatchers.Main)
                    .asLiveData()
            }

            Destination.UpComing -> {
                responseCommonPagingData = movieRepo
                    .getUpComingMoviePaging()
                    .cachedIn(viewModelScope + Dispatchers.Main)
                    .asLiveData()
            }
        }
    }

    override fun onClickItem(id:Int) {
       _clickItemEvent.postValue(Event(id))
    }
}