package com.sajjadio.trailers.domain.mapper

interface Mapper<I, O> {
    fun mapTo(input: I): O
}