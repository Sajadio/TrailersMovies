package com.example.trailers.utils

import android.content.SharedPreferences

class MovieSharedPreferences(private val prefManager: SharedPreferences) {

    fun updateThemeUI(key:String,value:Int){
        val editor = prefManager.edit()
        editor.putInt(key,value)
        editor.apply()
    }

    fun getThemeUI(key:String) = prefManager.getInt(key,0)

}