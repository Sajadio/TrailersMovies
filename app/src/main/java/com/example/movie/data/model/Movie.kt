package com.example.movie.data.model

data class Movie(
    val ids: Int,
    val trend: List<Trend>,
    val category: List<Category>,
    val type: List<Type>
)