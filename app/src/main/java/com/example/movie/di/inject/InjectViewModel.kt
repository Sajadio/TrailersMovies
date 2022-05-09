package com.example.movie.di.inject

import androidx.lifecycle.ViewModelProvider
import com.example.movie.di.annotate.ViewModelKey
import com.example.movie.ui.fragment.home.HomeFragment
import com.example.movie.ui.vm.MDBViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

/* Module that uses bound fragment and provided factory uses ViewModelProviders
 * to provide instance of FeatureViewModel
 */
@Module
class InjectViewModel {

    // Associate this provider method with MDBViewModel type in a generated map
    @Provides
    @IntoMap
    @ViewModelKey(MDBViewModel::class)
    fun provideMDBViewModel(
        factory: ViewModelProvider.Factory,
        target: HomeFragment,
    ) = ViewModelProvider(target, factory)[MDBViewModel::class.java]
}
