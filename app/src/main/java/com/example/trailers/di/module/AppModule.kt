package com.example.trailers.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import javax.inject.Provider
import javax.inject.Singleton

@Module
class AppModule {

    /* Singleton factory that searches generated map for specific provider and
        uses it to get a ViewModel instance */
    @Provides
    @Singleton
    fun provideViewModelFactory(
        providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>,
    ) = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            val creator = providers[modelClass] ?: providers.asIterable().firstOrNull {
                modelClass.isAssignableFrom(it.key)
            }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")

            return try {
                creator.get() as T
            } catch (e: Exception) {
                throw RuntimeException(e)
            }

        }
    }
}
