package com.example.movie.domain.repository

import com.example.movie.domain.network.ApiProvider
import com.example.movie.domain.network.ApiService
import java.util.concurrent.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MDBRepo @Inject constructor(
    private val apiService: ApiService
) {

    fun getAllItems() = ApiProvider.getAllItems()
    fun getCategory() = ApiProvider.getCategory()
    fun getType() = ApiProvider.getType()
}