package com.example.trailers.ui.fragment.home.vm


import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.trailers.R
import com.example.trailers.data.model.genre.Genre
import com.example.trailers.data.model.movie.common.CommonResult
import com.example.trailers.data.model.movie.genremovie.MovieResult
import com.example.trailers.data.repository.home.HomeRepo
import com.example.trailers.data.repository.common.CommonRepo
import com.example.trailers.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeViewModel @Inject constructor(
    private val homeRepo: HomeRepo,
    private val commonRepo: CommonRepo,
) : ViewModel() {

    private val _responseData: MutableLiveData<List<MultiViewTypeItem<NetworkStatus<Any>>>> =
        MutableLiveData()
    val responseData: LiveData<List<MultiViewTypeItem<NetworkStatus<Any>>>> = _responseData

    lateinit var getGenresMovie: LiveData<NetworkStatus<Genre?>>


    private val multiViewType = mutableSetOf<MultiViewTypeItem<NetworkStatus<Any>>>()


    // Paging
    lateinit var responseCommonPagingData: LiveData<PagingData<CommonResult>>
    lateinit var getGenresOfMovie: LiveData<PagingData<MovieResult>>

    init {
        checkConnection(R.string.connected)
    }

    fun checkConnection(isConnection: Int): Boolean {
        if (isConnection == R.string.connected) {
            viewModelScope.launch {

                getGenresMovie = commonRepo.getGenresMovie().flowOn(Dispatchers.Main).asLiveData()

                val playNow = async(Dispatchers.IO) { getTrend() }
                val popular = async(Dispatchers.IO) { getPopular() }
                val rated = async(Dispatchers.IO) { getTopRated() }
                val coming = async(Dispatchers.IO) { getUpComing() }

                if (playNow.await() == popular.await() && rated.await() == coming.await())
                    withContext(Dispatchers.Main) {
                        _responseData.postValue(multiViewType.toList().sortedBy { it.viewType })
                    }
            }
            return false
        } else {
            return true
        }
    }

    fun getGenresOfMovie(genreId: String) {
        getGenresOfMovie =
            commonRepo.getGenreList(genreId = genreId).cachedIn(viewModelScope)
                .flowOn(Dispatchers.Main).asLiveData()
    }

    fun checkDestination(id: Int) {
        when (id) {
            Constant.POPULAR -> responseCommonPagingData =
                commonRepo.getPopularMoviePaging().cachedIn(viewModelScope).flowOn(Dispatchers.Main)
                    .asLiveData()
            Constant.TOP_RATED -> responseCommonPagingData =
                commonRepo.getTopRatedMoviePaging().cachedIn(viewModelScope)
                    .flowOn(Dispatchers.Main).asLiveData()
            Constant.UP_COMING -> responseCommonPagingData =
                commonRepo.getUpComingMoviePaging().cachedIn(viewModelScope)
                    .flowOn(Dispatchers.Main).asLiveData()
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

