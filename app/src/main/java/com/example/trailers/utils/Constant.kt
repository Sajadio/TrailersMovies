package com.example.trailers.utils

import com.example.trailers.R

object Constant {
    const val STORAGE_NAME = "data_Storage"

    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_PATH = "https://image.tmdb.org/t/p/"
    const val API_KEY = "24ada3556f9c3697c024bc6ba0f1ab99"
    const val YOUTUBE_BASE = "https://www.youtube.com/watch?v="

    const val IMAGE_Size_45 = "w45/"
    const val IMAGE_Size_100 = "w100/"
    const val IMAGE_Size_200 = "w200/"
    const val IMAGE_Size_300 = "w300/"
    const val IMAGE_Size_400 = "w400/"
    const val IMAGE_Size_500 = "w500/"
    const val IMAGE_Size_780 = "w780/"
    const val IMAGE_Size_H632 = "h632/"

    const val IMAGE_Size_ORIGINAL = "original/"

    const val POPULAR = R.string.popular
    const val TOP_RATED: Int = R.string.rated
    const val UP_COMING = R.string.upComing

    const val SYSTEM_DEFAULT = "systemDefault"
    const val LIGHT = "light"
    const val DARK = "dark"

    const val PREFERENCE_NAME = "my_preference"
    const val THEME_APP = "selectTheme"
    const val DEFAULT_PAGE_SIZE = 20
    const val DEFAULT_PAGE_INDEX = 1

    const val TAG = "TestingResult"


    fun allGenres() = mapOf(
        "28" to "Action",
        "12" to "Adventure",
        "16" to "Animation",
        "35" to "Comedy",
        "80" to "Crime",
        "99" to "Documentary",
        "18" to "Drama",
        "10751" to "Family",
        "14" to "Fantasy",
        "36" to "History",
        "27" to "Horror",
        "10402" to "Music",
        "9648" to "Mystery",
        "10749" to "Romance",
        "878" to "Fiction",
        "10770" to "Movie",
        "53" to "Thriller",
        "10752" to "War",
        "37" to "Western",

        )

}