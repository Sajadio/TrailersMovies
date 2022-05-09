package com.example.movie.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

val listChips = mutableListOf(
    "Action",
    "Drama",
    "Fantastic",
    "Kamidi",
    "Horro",
    "Crime",
    "Action",
    "Fantastic",
    "Crime"
)

fun createWithFactory(
    create: () -> ViewModel
): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")// Casting T as ViewModel
            return create.invoke() as T
        }
    }
}