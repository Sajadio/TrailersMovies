package com.example.trailers.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.trailers.di.annotate.ViewModelKey
import com.example.trailers.ui.activity.HomeActivity
import com.example.trailers.ui.fragment.common.CommonFragment
import com.example.trailers.ui.fragment.common.vm.CommonViewModel
import com.example.trailers.ui.fragment.genres.GenresFragment
import com.example.trailers.ui.fragment.genres.vm.GenresViewModel
import com.example.trailers.ui.fragment.home.HomeFragment
import com.example.trailers.ui.fragment.home.vm.HomeViewModel
import com.example.trailers.ui.fragment.home.vm.StorageViewModel
import com.example.trailers.ui.fragment.search.SearchFragment
import com.example.trailers.ui.fragment.search.vm.SearchViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

/* Module that uses bound fragment and provided factory uses ViewModelProviders
 * to provide instance of FeatureViewModel
 */
@Module
class ProviderViewModel {

    // Associate this provider method with HomeViewModel type in a generated map
    @Provides
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun provideMDBViewModel(
        factory: ViewModelProvider.Factory,
        target: HomeFragment,
    ) = ViewModelProvider(target, factory)[HomeViewModel::class.java]

    @Provides
    @IntoMap
    @ViewModelKey(CommonViewModel::class)
    fun provideCommonViewModel(
        factory: ViewModelProvider.Factory,
        target: CommonFragment,
    ) = ViewModelProvider(target, factory)[CommonViewModel::class.java]


    @Provides
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun provideSearchViewModel(
        factory: ViewModelProvider.Factory,
        target: SearchFragment,
    ) = ViewModelProvider(target, factory)[SearchViewModel::class.java]

    @Provides
    @IntoMap
    @ViewModelKey(StorageViewModel::class)
    fun provideStorageViewModel(
        factory: ViewModelProvider.Factory,
        target: HomeActivity
    ) = ViewModelProvider(target, factory)[StorageViewModel::class.java]

    @Provides
    @IntoMap
    @ViewModelKey(GenresViewModel::class)
    fun provideGenresViewModel(
        factory: ViewModelProvider.Factory,
        target: GenresFragment
    ) = ViewModelProvider(target, factory)[GenresViewModel::class.java]

}