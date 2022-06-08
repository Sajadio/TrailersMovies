package com.example.trailers.data.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.trailers.R
import com.example.trailers.utils.UiMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


interface DataStorage {
    val uiModeFlow: Flow<String>
    suspend fun setUIMode(uiMode: UiMode)
}