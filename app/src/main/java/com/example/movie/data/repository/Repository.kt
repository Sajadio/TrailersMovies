package com.example.movie.data.repository

import com.example.movie.data.network.ApiProvider

object Repository {

    fun getAllItems() = ApiProvider.getAllItems()
    fun getCategory() = ApiProvider.getCategory()
    fun getType() = ApiProvider.getType()
}