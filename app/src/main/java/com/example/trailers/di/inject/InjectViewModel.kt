package com.example.trailers.di.inject

import androidx.lifecycle.ViewModelProvider
import com.example.trailers.di.annotate.ViewModelKey
import com.example.trailers.ui.fragment.home.HomeFragment
import com.example.trailers.ui.vm.MDBViewModel
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
