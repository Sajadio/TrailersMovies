package com.example.movie.ui.fragment.home.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie.data.model.Movie
import com.example.movie.data.repository.Repository
class HomeViewModel {

    private val _movieLiveData =  MutableLiveData<List<Movie>>()
     val movieLiveData : LiveData<List<Movie>> = _movieLiveData


}