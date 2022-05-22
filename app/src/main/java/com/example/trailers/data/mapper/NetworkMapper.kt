package com.example.trailers.data.mapper

interface NetworkMapper<From, To> {
    fun mapFrom(network: From): To
}