package com.example.trailers.utils

import com.example.trailers.R

object Constant {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_PATH = "https://image.tmdb.org/t/p/w500/"
    const val YOUTUBE_BASE = "https://www.youtube.com/watch?v="
    const val YOUTUBE_KEY = "AIzaSyC6YnlwPt9XHT6EY22WYbVcX9JD5osQ3_I"
    const val API_KEY = "24ada3556f9c3697c024bc6ba0f1ab99"

    const val POPULAR = R.string.popular
    const val TOP_RATED: Int = R.string.rated
    const val UP_COMING = R.string.upComing

    const val ENGLISH = "en"
    const val ARABIC = "ar"
    const val LIGHT = R.string.light
    const val DARK = R.string.dark
    const val SYSTEM_DEFAULT = R.string.systemDefault
    const val PREFERENCE_NAME = "my_preference"
    const val THEME_APP = "Theme"
    const val BROADCAST_STRING_FOR_ACTION = "checkInternet"
    const val ONLINE_STATUS = "online_status"
    const val INITIAL_PAGE = 1
    const val DEFAULT_PAGE_INDEX = 1
    const val DEFAULT_PAGE_SIZE = 20


    fun genresMovie(): HashMap<Int, String> {
        val genre = hashMapOf<Int, String>()
        genre[28] = "Action"
        genre[12] = "Adventure"
        genre[16] = "Animation"
        genre[35] = "Comedy"
        genre[80] = "Crime"
        genre[99] = "Documentary"
        genre[18] = "Drama"
        genre[10751] = "Family"
        genre[14] = "Fantasy"
        genre[36] = "History"
        genre[27] = "Horror"
        genre[10402] = "Music"
        genre[9648] = "Mystery"
        genre[10749] = "Romance"
        genre[878] = "Fiction"
        genre[10770] = "MovieResult"
        genre[53] = "Thriller"
        genre[10752] = "War"
        genre[37] = "Western"

        return genre

    }

}