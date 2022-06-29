package com.example.trailers.data.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.trailers.R
import com.example.trailers.utils.Constant
import com.example.trailers.utils.UiMode
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DataStorageImp @Inject constructor(@ApplicationContext context: Context) : DataStorage {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constant.STORAGE_NAME)
    private val dataStore = context.dataStore

    //keys
    private companion object {
        val SELECTED_THEME = stringPreferencesKey(Constant.THEME_APP)
    }


    // get the value according to condition
    override val uiModeFlow: Flow<String> = dataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preference ->
            when (preference[SELECTED_THEME]) {
                Constant.SYSTEM_DEFAULT -> Constant.SYSTEM_DEFAULT
                Constant.LIGHT -> Constant.LIGHT
                Constant.DARK -> Constant.DARK
                else -> ""
            }
        }

    // update value in storage
    override suspend fun setUIMode(uiMode: UiMode) {
        dataStore.edit { preferences ->
            preferences[SELECTED_THEME] = when (uiMode) {
                UiMode.SYSTEM_DEFAULT -> Constant.SYSTEM_DEFAULT
                UiMode.LIGHT -> Constant.LIGHT
                UiMode.DARK -> Constant.DARK
            }.toString()
        }
    }
}