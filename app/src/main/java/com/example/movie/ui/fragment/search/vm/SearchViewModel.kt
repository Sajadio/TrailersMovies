package com.example.movie.ui.fragment.search.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.domain.model.movie.search.Search
import com.example.movie.domain.repository.SearchRepo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchViewModel @Inject constructor(private val searchRepo: SearchRepo) : ViewModel() {

    private val _getMoviesSearch: MutableLiveData<Search> = MutableLiveData()
    val getMoviesSearch: LiveData<Search> = _getMoviesSearch

    private val _getErrorSearch: MutableLiveData<ResponseBody> = MutableLiveData()
    val getErrorSearch: LiveData<ResponseBody> = _getErrorSearch


    fun getMovieSearch(query:String) {
        viewModelScope.launch {
            val response = searchRepo.getMoviesSearch(query = query)
            if (response.isSuccessful)
                _getMoviesSearch.postValue(response.body())
            else
                _getErrorSearch.postValue(response.errorBody())

        }
    }


}