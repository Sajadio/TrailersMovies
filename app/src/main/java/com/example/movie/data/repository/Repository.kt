package com.example.movie.data.repository

import com.example.movie.data.network.ApiProvider

object Repository {

    fun getAllItems() = ApiProvider.getAllItems()
    fun getSize() = ApiProvider.getCategory()
}