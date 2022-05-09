package com.example.movie.ui.vm

import androidx.lifecycle.*
import com.example.movie.data.m.Trend
import com.example.movie.domain.model.movie.search.Search
import com.example.movie.domain.model.movie.trend.Trending
import com.example.movie.domain.repository.MDBRepo
import kotlinx.coroutines.launch
import javax.inject.Inject

class MDBViewModel @Inject constructor (private val mdbRepo: MDBRepo):ViewModel() {

}