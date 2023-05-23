package com.sajjadio.trailers.data.storage

import com.sajjadio.trailers.utils.UiMode
import kotlinx.coroutines.flow.Flow


interface DataStorage {
    val uiModeFlow: Flow<String>
    suspend fun setUIMode(uiMode: UiMode)
}