package com.example.movie.ui.fragment.home.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie.data.m.Movie

class HomeViewModel {

    private val _movieLiveData =  MutableLiveData<List<Movie>>()
     val movieLiveData : LiveData<List<Movie>> = _movieLiveData


}